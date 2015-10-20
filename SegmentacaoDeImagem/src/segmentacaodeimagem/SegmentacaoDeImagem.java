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

 /*  
    public static ImageInformation printLabelsMap(ImageInformation image) {
        image.getRegionMarkedImage().setRGB(0,0,121);        
    }
 */
    public static void main(String[] args) {
                        
        // Segmentação com parâmetros 0.99, 40 e 1000
        ImageInformation seg = ImageSegmentation.performSegmentation("imgs/imd.jpg", 0.99,40,1000);
        //BufferedImage image = seg.getRegionMarkedImage(); // imagem para testar o mapa de rótulos
        
        int height = seg.getRegionMarkedImage().getHeight(); // altura da imagem
        int width = seg.getRegionMarkedImage().getWidth(); // largura da imagem
  
        int[] pixel; // array que contém as coordenadas x,y e o valor rgb do pixel.
        int[] mapaDaRegiaoSegmentada = seg.getSegmentedImageMap();
        int[] pixelsDaImagemSegmentada = seg.getRegionMarkedPixels();
        int[] variacaoGray = new int[seg.getTotalRegions()];
        
        int defGrey = 255/seg.getTotalRegions();
        
        
        for(int i = 0; i < seg.getTotalRegions(); i++) {
            variacaoGray[i] = defGrey*i;
        }
        
        Color grey = new Color(121, 121, 121); // uma cor para teste
        int rgb = grey.getRGB();

        // Impressão na tela da quantidade de regiões gerada
        //System.out.println("Total de regiões: " + seg.getTotalRegions());
        //System.out.println("Tamanho: " + seg.getRegionMarkedPixels().length + " | Pixels RGB da Imagem Segmentada: " + Arrays.toString(seg.getRegionMarkedPixels()));
        //System.out.println("Tamanho: " + seg.getOriginalPixels().length + " | Pixels RGB da Imagem Originalxx: " + Arrays.toString(seg.getOriginalPixels()));
        //System.out.println("Tamanho: " + seg.getSegmentedImageMap().length + " | Mapa da Imagem Segmentada: " + Arrays.toString(seg.getSegmentedImageMap()));        
        
        // Criação de um JFrame e inserção de 2 JLabels com cada uma das imagens.
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getOriginalImage()))); // Imagem original
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); // Imagem segmentada             
        
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

        int gray = 0;
        int rgb2 = 0;
        for(int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            gray = variacaoGray[mapaDaRegiaoSegmentada[i]];
            rgb2 = ((gray&0x0ff)<<16)|((gray&0x0ff)<<8)|(gray&0x0ff);
            pixelsDaImagemSegmentada[i] = rgb2;
            
        }         
        
        
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); // Imagem segmentada 
        
        frame.pack();
        frame.setVisible(true);
    }         
}
