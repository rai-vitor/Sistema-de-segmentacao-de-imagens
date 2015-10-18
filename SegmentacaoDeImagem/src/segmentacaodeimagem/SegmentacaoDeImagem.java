/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segmentacaodeimagem;

import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;



/**
 *
 * @author hiago
 */
public class SegmentacaoDeImagem {

    /**
     * @param args the command line arguments
     */
 
    
    public SegmentacaoDeImagem() {

    }

 /*  
    public static ImageInformation printLabelsMap(ImageInformation image) {
        image.getRegionMarkedImage().setRGB(0,0,121);        
    }
 */
    public static void main(String[] args) {
        // Segmentação com parâmetros 0.99, 40 e 1000
        ImageInformation seg = ImageSegmentation.performSegmentation("imgs/imd.jpg", 0.99,40,1000);
        BufferedImage image = seg.getRegionMarkedImage();

        // Impressão na tela da quantidade de regiões gerada
        System.out.println("Total de regiões: " + seg.getTotalRegions());
        System.out.println( seg.getRegionMarkedPixels().length + "Pixels RGB da Imagem Segmentada: " + Arrays.toString(seg.getRegionMarkedPixels()));
        System.out.println( seg.getOriginalPixels().length + "Pixels RGB da Imagem Originalxx: " + Arrays.toString(seg.getOriginalPixels()));
        System.out.println( seg.getSegmentedImageMap().length + "Mapa da Imagem Segmentada: " + Arrays.toString(seg.getSegmentedImageMap()));
        
        
        int height = seg.getRegionMarkedImage().getHeight();
        int width = seg.getRegionMarkedImage().getWidth();
  
        int[] pixel;
        int ints[] = new int[seg.getRegionMarkedPixels().length];
        ints = seg.getSegmentedImageMap();
        
        Color myWhite = new Color(255, 255, 255); // Color white
        int rgb2=myWhite.getRGB();
        
        
        // Criação de um JFrame e inserção de 2 JLabels com cada uma das imagens.
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getOriginalImage()))); // Imagem original
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); // Imagem segmentada

        int aux = 0;
        ArrayList<Integer> elements = new ArrayList<>();       
        
        /**
         * Retorna as coordenadas de cada pixel da imagem.
         */

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixel = seg.getRegionMarkedImage().getRaster().getPixel(x,y, new int[3]);                               
                System.out.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2]);
                if(ints[x] == 9) {
                    image.setRGB(x, y, rgb2);
                    System.out.println("oks");
                }
            }
        }
       
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));      

        
        
        frame.pack();
        frame.setVisible(true);
    }         
}
