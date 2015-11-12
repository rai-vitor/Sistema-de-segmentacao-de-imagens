package segmentacaodeimagem;

import java.awt.Color;

public class Filtro {
    
    /**
     * Aplica um filtro na imagem, onde a(s) região ou regiões que não foram 
     * selecionadas têm o seu brilho reduzido. 
     * @param img
     */
    public static void EscurecerPixel(Imagem img){
        Color c;
        int red;
        int green;
        int blue;
        int rgb;
        
        //Toda vez eu restauro img para a original. Assim garanto que somente as outras regiões serão apagadas.
        img.RestaurarImg(0);
        
        for(int i = 0; i < img.getPixelsDaImagemSegmentada().length; i++) {
            if(!img.getPixelRegion().contains(img.getMapaDaRegiaoSegmentada()[i])) {
                 c = new Color(img.getPixelsDaImagemSegmentada()[i]);
                 red = c.getRed();
                 green = c.getBlue();
                 blue = c.getBlue();
                 rgb = (((red/3)&0x0ff)<<16)|(((green/3)&0x0ff)<<8)|((blue/3)&0x0ff);
                 img.getPixelsDaImagemSegmentada()[i] = rgb; 
             } 
        }
    }
}
