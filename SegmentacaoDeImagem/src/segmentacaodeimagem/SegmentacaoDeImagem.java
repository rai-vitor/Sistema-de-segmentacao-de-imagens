/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segmentacaodeimagem;

import boofcv.alg.segmentation.ComputeRegionMeanColor;
import boofcv.alg.segmentation.ImageSegmentationOps;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.factory.segmentation.FactorySegmentationAlg;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.feature.VisualizeRegions;
import boofcv.gui.image.ShowImages;
import boofcv.struct.feature.ColorQueue_F32;
import boofcv.struct.image.ImageBase;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageSInt32;
import boofcv.struct.image.ImageType;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import org.ddogleg.struct.FastQueue;
import org.ddogleg.struct.GrowQueue_I32;


/**
 *
 * @author hiago
 */
public class SegmentacaoDeImagem {

    /**
     * @param args the command line arguments
     */
    
    private static int[] mapaDaRegiaoSegmentada;
    private static int[] pixelsDaImagemSegmentada; 
    private static int[] variacaoGray;
    private static int defGrey;

    public static ImageInformation segmentar(String path, double blur, int radius, int size) {
                        
        // Segmentação com parâmetros 0.99, 40 e 1000
        ImageInformation seg = ImageSegmentation.performSegmentation(path, blur,radius,size);

        mapaDaRegiaoSegmentada = seg.getSegmentedImageMap();
        pixelsDaImagemSegmentada = seg.getRegionMarkedPixels();
        variacaoGray = new int[seg.getTotalRegions()];        
        defGrey = 255/seg.getTotalRegions();
                   
       
        // Criação de um JFrame e inserção de 2 JLabels com cada uma das imagens.
        /*
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getOriginalImage()))); // Imagem original
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); // Imagem segmentada             
        */
        
        /*
        for(int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            gray = variacaoGray[mapaDaRegiaoSegmentada[i]];
            rgb = ((gray&0x0ff)<<16)|((gray&0x0ff)<<8)|(gray&0x0ff);
            pixelsDaImagemSegmentada[i] = rgb;            
        } */        
         /*       
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); // Imagem segmentada         
        frame.pack();
        frame.setVisible(true);
                 */
        
        return seg;
    }       
    
    /**
         * Mapa de Rótulos...
         * Precisamos determinar a cor dos pixels de cada região da imagem segmentada.
         * As regiões podem ser determinadas através do array retornado pelo método seg.getSegmentedImageMap(). 
         * Cada região tem um número fixo que está associado a cada pixel da imagem de forma sequencial (não tenho certeza),
         * basta comparar os arrays seg.getRegionMarkedPixels() e seg.getSegmentedImageMap(), eles possuem o mesmo tamanho.
         * 
         * A imagem deve ser construída a partir da informação das regiões obtidas através do mapa de rótulos 
         * (método getSegmentedImageMap) e do número total de regiões (método getTotalRegions).
         * 
         * A função abaixo retorna as coordenadas de cada pixel da imagem, além do seu RGB.
         * Também define a cor do pixel através do parâmetro setRGB.
         */
    
    public static ImageInformation rotular(ImageInformation imagemSegmentada) {
        
        int gray = 0;
        int rgb = 0;        
        
        for(int i = 0; i < imagemSegmentada.getTotalRegions(); i++) {
            variacaoGray[i] = defGrey*i;
        }    
        
        for(int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            gray = variacaoGray[mapaDaRegiaoSegmentada[i]];
            rgb = ((gray&0x0ff)<<16)|((gray&0x0ff)<<8)|(gray&0x0ff);
            pixelsDaImagemSegmentada[i] = rgb;            
        }
        
        return imagemSegmentada;
    }        
}
