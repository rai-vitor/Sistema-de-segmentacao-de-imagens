package segmentacaodeimagem;

import boofcv.abst.segmentation.ImageSuperpixels;
import boofcv.alg.filter.blur.GBlurImageOps;
import boofcv.alg.segmentation.ComputeRegionMeanColor;
import boofcv.alg.segmentation.ImageSegmentationOps;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.factory.segmentation.FactoryImageSegmentation;
import boofcv.factory.segmentation.FactorySegmentationAlg;
import boofcv.gui.feature.VisualizeRegions;
import boofcv.struct.feature.ColorQueue_F32;
import boofcv.struct.image.ImageBase;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageSInt32;
import boofcv.struct.image.ImageType;
import java.awt.image.BufferedImage;
import org.ddogleg.struct.FastQueue;
import org.ddogleg.struct.GrowQueue_I32;

/**
 * Example demonstrating high level image segmentation interface. An image
 * segmented using this interface will have each pixel assigned a unique label
 * from 0 to N-1, where N is the number of regions. All pixels which belong to
 * the same region are connected. These regions are also known as superpixels.
 *
 * @author Peter Abeles
 */
public class ExampleSegmentSuperpixels {

    /**
     * Segments and visualizes the image
     * @param <T>
     * @param alg
     * @param color
     * @return 
     */
    public static <T extends ImageBase>
            BufferedImage performSegmentation(ImageSuperpixels<T> alg, T color) {
		// Segmentation often works better after blurring the image.  Reduces high frequency image components which
        // can cause over segmentation
        GBlurImageOps.gaussian(color, color, 0.5, -1, null);

		// Storage for segmented image.  Each pixel will be assigned a label from 0 to N-1, where N is the number
        // of segments in the image
        ImageSInt32 pixelToSegment = new ImageSInt32(color.width, color.height);

        // Segmentation magic happens here
        alg.segment(color, pixelToSegment);

        // Displays the results
        return visualize(pixelToSegment, color, alg.getTotalSuperpixels());
    }

    /**
     * Visualizes results three ways. 1) Colorized segmented image where each
     * region is given a random color. 2) Each pixel is assigned the mean color
     * through out the region. 3) Black pixels represent the border between
     * regions.
     * @param <T>
     * @param pixelToRegion
     * @param color
     * @param numSegments
     * @return 
     */
    public static <T extends ImageBase>
            BufferedImage visualize(ImageSInt32 pixelToRegion, T color, int numSegments) {
        // Computes the mean color inside each region
        ImageType<T> type = color.getImageType();
        ComputeRegionMeanColor<T> colorize = FactorySegmentationAlg.regionMeanColor(type);

        FastQueue<float[]> segmentColor = new ColorQueue_F32(type.getNumBands());
        segmentColor.resize(numSegments);

        GrowQueue_I32 regionMemberCount = new GrowQueue_I32();
        regionMemberCount.resize(numSegments);

        ImageSegmentationOps.countRegionPixels(pixelToRegion, numSegments, regionMemberCount.data);
        colorize.process(color, pixelToRegion, regionMemberCount, segmentColor);

        // Draw each region using their average color
        BufferedImage outColor = VisualizeRegions.regionsColor(pixelToRegion, segmentColor, null);
        // Draw each region by assigning it a random color
        BufferedImage outSegments = VisualizeRegions.regions(pixelToRegion, numSegments, null);

        // Make region edges appear red
        BufferedImage outBorder = new BufferedImage(color.width, color.height, BufferedImage.TYPE_INT_RGB);
        ConvertBufferedImage.convertTo(color, outBorder, true);
        VisualizeRegions.regionBorders(pixelToRegion, 0xFF0000, outBorder);
        /*
         // Show the visualization results
         ListDisplayPanel gui = new ListDisplayPanel();
         gui.addImage(outColor,"Color of Segments");
         gui.addImage(outBorder, "Region Borders");
         gui.addImage(outSegments, "Regions");
         ShowImages.showWindow(gui,"Superpixels", true);
         *//*
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(outColor)));
        frame.getContentPane().add(new JLabel(new ImageIcon(outBorder)));
        frame.getContentPane().add(new JLabel(new ImageIcon(outSegments)));
        frame.pack();
        frame.setVisible(true);*/
        return outColor;
    }

    public static BufferedImage FazerTudo(BufferedImage image) {
        //BufferedImage image = UtilImageIO.loadImage("imgs/imd.jpg");
        //BufferedImage image = UtilImageIO.loadImage("../data/applet/segment/berkeley_kangaroo.jpg");
        //BufferedImage image = UtilImageIO.loadImage("../data/applet/segment/berkeley_man.jpg");
        //BufferedImage image = UtilImageIO.loadImage("../data/applet/segment/mountain_pines_people.jpg");
        //BufferedImage image = UtilImageIO.loadImage("../data/applet/particles01.jpg");

        // Select input image type.  Some algorithms behave different depending on image type
        //ImageType<MultiSpectral<ImageFloat32>> imageType = ImageType.ms(3, ImageFloat32.class);
        //ImageType<MultiSpectral<ImageUInt8>> imageType = ImageType.ms(3,ImageUInt8.class);
	ImageType<ImageFloat32> imageType = ImageType.single(ImageFloat32.class);
        //ImageType<ImageUInt8> imageType = ImageType.single(ImageUInt8.class);

	ImageSuperpixels alg = FactoryImageSegmentation.meanShift(null, imageType);
        //ImageSuperpixels alg = FactoryImageSegmentation.slic(new ConfigSlic(400), imageType);
        //ImageSuperpixels alg = FactoryImageSegmentation.fh04(new ConfigFh04(100, 30), imageType);
        //ImageSuperpixels alg = FactoryImageSegmentation.watershed(null,imageType);

        // Convert image into BoofCV format
        ImageBase color = imageType.createImage(image.getWidth(), image.getHeight());
        ConvertBufferedImage.convertFrom(image, color, true);

        // Segment and display results
        return performSegmentation(alg, color);
    }
}