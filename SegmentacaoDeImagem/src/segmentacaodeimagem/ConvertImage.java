package segmentacaodeimagem;

import database.DataBase;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Uma classe que converte a resolução da imagem a ser trabalhada para uma resolução padrão.
 * @author Hiago Miguel.
 */

public class ConvertImage {
    
    private static String caminhoDaImagem;
        
    /**
     * Redimensiona a imagem para uma resolução padrão.
     * 
     * @param WIDTH Nova largura da imagem.
     * @param HEIGHT Nova altura da imagem.
     * @param filename Caminho da imagem a ser dimensionada. 
     */
    
    public static void scaleImage(int WIDTH, int HEIGHT, String filename) {
                
        BufferedImage image = null;
        
        try {
            ImageIcon icon = new ImageIcon(filename);//path to image
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) image.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(icon.getImage(), 0, 0, WIDTH, HEIGHT, null);
        } 
        catch (Exception e) {
            e.getMessage();            
        }
                        
        saveToFile(image);
    }
    
    /**
     * Salva imagem redimensionada.
     * 
     * @param image Imagem a ser salva.
     */
    public static void saveToFile(BufferedImage image) {
        DataBase db = DataBase.getInstance();
        int num = db.CountImg();
        String path = "imgs/img"+num+".jpg";
        File outputfile = new File(path);
        
        try {
            ImageIO.write(image, "jpg", outputfile);
        } 
        catch (IOException ex) {
            Logger.getLogger(ConvertImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setCaminhoDaImagem(path);
    }
    
    /**
     * Define o local onde a imagem será salva.
     * @param caminho Local onde a imagem será salva.
     */
    public static void setCaminhoDaImagem(String caminho) {
        caminhoDaImagem = caminho;
    }
    
    /**
     * Retorna o caminho onde a imagem está salva.
     * @return Caminho onde a imagem está salva.
     */
    public static String getCaminhoDaImagem() {
        return caminhoDaImagem;
    }
}
