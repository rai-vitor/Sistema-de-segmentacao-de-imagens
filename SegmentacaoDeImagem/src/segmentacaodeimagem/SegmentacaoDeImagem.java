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

 /*  
    public static ImageInformation printLabelsMap(ImageInformation image) {
        image.getRegionMarkedImage().setRGB(0,0,121);        
    }
 */
    public static void main(String[] args) {
                        
        // Segmentação com parâmetros 0.99, 40 e 1000
        ImageInformation seg = ImageSegmentation.performSegmentation("imgs/imd.jpg", 0.99,40,1000);
        BufferedImage image = seg.getRegionMarkedImage(); // imagem para testar o mapa de rótulos
        
        int height = seg.getRegionMarkedImage().getHeight(); // altura da imagem
        int width = seg.getRegionMarkedImage().getWidth(); // largura da imagem
  
        int[] pixel; // array que contém as coordenadas x,y e o valor rgb do pixel.
        int[] mapaDaRegiaoSegmentada = seg.getSegmentedImageMap();
        int[] pixelsDaImagemSegmentada = seg.getRegionMarkedPixels();
        
        Color grey = new Color(121, 121, 121); // uma cor para teste
        int rgb = grey.getRGB();

        // Impressão na tela da quantidade de regiões gerada
        System.out.println("Total de regiões: " + seg.getTotalRegions());
        System.out.println("Tamanho: " + seg.getRegionMarkedPixels().length + " | Pixels RGB da Imagem Segmentada: " + Arrays.toString(seg.getRegionMarkedPixels()));
        System.out.println("Tamanho: " + seg.getOriginalPixels().length + " | Pixels RGB da Imagem Originalxx: " + Arrays.toString(seg.getOriginalPixels()));
        System.out.println("Tamanho: " + seg.getSegmentedImageMap().length + " | Mapa da Imagem Segmentada: " + Arrays.toString(seg.getSegmentedImageMap()));        
        
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
         * A função abaixo retorna as coordenadas de cada pixel da imagem, além do seu RGB.
         * Também define a cor do pixel através do parâmetro setRGB.
         */

        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixel = seg.getRegionMarkedImage().getRaster().getPixel(x,y, new int[3]);
                //image.setRGB(x, y, rgb); // define a cor do pixel
                System.out.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2]);                
            }
        }
        
/*        
        int regiao = 0;
        int tmp = 0;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixel = seg.getRegionMarkedImage().getRaster().getPixel(x,y, new int[3]);
                if(mapaDaRegiaoSegmentada[x] == 9) {
                    seg.getRegionMarkedImage().setRGB(x, y, rgb);
                }
                System.out.println("Working? " + x + " - " + " - " + y + " - " + rgb);
            }
        }                
  */     

        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); //mapa de rótulos :/      
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
