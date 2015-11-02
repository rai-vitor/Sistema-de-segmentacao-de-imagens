package segmentacaodeimagem;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Uma classe que realiza a segmentação de uma imagem, bem como o mapa de rótulos da mesma.
 * @author Hiago Miguel & Rai Vitor.
 */
public class SegmentacaoDeImagem {      

    /**
     * Array de inteiros que contem mapa de regioes [0−N ] da segmentacao.
     * O mapa de regioes tem o mesmo tamanho do array 'pixelsDaImagemSegmentada',
     * para cada pixel da imagem, uma regiao [0-N] é definida.
     */
    private static int[] mapaDaRegiaoSegmentada;
    /* Array de inteiros que contem os pixels da imagem RGB segmentada.*/
    private static int[] pixelsDaImagemSegmentada; 
    /* Array de inteiros que contem uma tonalidade cinza especifica para cade regiao [0-N] da segmentacao.*/
    private static int[] variacaoGray;
    /* Define a intensidade da tonalidade cinza inicial a ser utilizada*/
    private static int defGrey;
    
    private static int coordinateX;
    private static int coordinateY;
    private static ArrayList<Integer> pixelRegion, pixelsDaImagemSegmentada2;
    private static Boolean isAnotation;
    
    
    /**
     * Segmenta uma dada imagem de acordo com os parâmetros abaixo.
     * 
     * @param path Caminho da imagem.
     * @param blur Nível de desfoque (blur) para suavizar arestas da imagem.
     * @param radius Principal parâmetro do algoritmo Mean Shift. Sua mudança é bastante sensível ao resultado da segmentação.
     * @param size Tamanho mínimo das regiões obtidas na segmentação.
     * @return Retorna objeto da classe ImageInformation, responsável por armazenar as informações da imagem original, 
     * a imagem segmentada, o mapa de regiões e a quantidade de regiões geradas após o processo de segmentação.
     */
    public static ImageInformation segmentar(String path, double blur, int radius, int size) {                        
        ImageInformation seg = ImageSegmentation.performSegmentation(path, blur,radius,size);
        mapaDaRegiaoSegmentada = seg.getSegmentedImageMap();
        pixelsDaImagemSegmentada = seg.getRegionMarkedPixels();
        
        variacaoGray = new int[seg.getTotalRegions()];        
        defGrey = 255/seg.getTotalRegions();
        pixelsDaImagemSegmentada2 = new ArrayList<Integer>();
        copiar();
        pixelRegion = new ArrayList<Integer>();
        isAnotation = false;
        return seg;
    }       
    
    private static void copiar(){
        for(int i=0; i<pixelsDaImagemSegmentada.length; i++){
            pixelsDaImagemSegmentada2.add(pixelsDaImagemSegmentada[i]);
        }
    }
    
    private static void backup(){
        for (int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            pixelsDaImagemSegmentada[i] = pixelsDaImagemSegmentada2.get(i);
        }
    }
    
    /**
     * Cria o mapa de rótulos de uma dada imagem segmentada.
     * 
     * A imagem é construída a partir da informação das regiões obtidas através do mapa de rótulos 
     * (método getSegmentedImageMap) e do número total de regiões (método getTotalRegions).
     * 
     * @param imagemSegmentada Imagem segmentada.
     * @return Retorna imagem segmentada rotulada.
    */
    public static ImageInformation GerarMapaRotulos(ImageInformation imagemSegmentada) {        
        /* Recebe a tonalidade cinza de uma regiao.*/
        int gray = 0;
        /* Pixel RGB de tonalidade cinza a ser mapeado para o array pixelsDaImagemSegmentada. */
        int rgb = 0;        
        
        GapGray(imagemSegmentada.getTotalRegions());
        
        for(int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            gray = variacaoGray[mapaDaRegiaoSegmentada[i]];
            rgb = ((gray&0x0ff)<<16)|((gray&0x0ff)<<8)|(gray&0x0ff);
            pixelsDaImagemSegmentada[i] = rgb;            
        }        
        return imagemSegmentada;
    }        
    
    /**
     * Gera espacamentos iguais entre os possiveis 255 cinzas
     * @param tamanhoRegiao - Quantidade de regioes existentes na imagem segmentada 
     */
    private static void GapGray(int tamanhoRegiao){
        for(int i = 0; i < tamanhoRegiao; i++) {
            variacaoGray[i] = defGrey*i;
        }
    }
    
    // define as coordenadas x,y de um pixel de acordo com o mouse click
    public static void setCoordenadas ( int x, int y, int altura, int largura) {
        System.out.println("Altura: "+altura+" Largura: "+largura);
        coordinateX = x;//largura
        coordinateY = y*largura;//altura
    }
    
    public static void getPixel(ImageInformation img) {
        int pixel = coordinateX + coordinateY;
        //se já tiver adicionado, não adiciona
        if(!pixelRegion.contains(mapaDaRegiaoSegmentada[pixel])){
            pixelRegion.add(mapaDaRegiaoSegmentada[pixel]);
            darkenPixels(img);
        }
        System.out.println("Indice array: "+pixel+" - Regiao: "+pixelRegion);
    }
    
    //muda cor dos pixels, para destacar a região selecionada
    public static ImageInformation darkenPixels(ImageInformation img) {
        
        Color c;
        int red;
        int green;
        int blue;
        int rgb;
        backup();
        for(int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            if(!pixelRegion.contains(mapaDaRegiaoSegmentada[i])) {
                 c = new Color(pixelsDaImagemSegmentada[i]);
                 red = c.getRed();
                 green = c.getBlue();
                 blue = c.getBlue();
                 rgb = (((red/2)&0x0ff)<<16)|(((green/2)&0x0ff)<<8)|((blue/2)&0x0ff);
                 pixelsDaImagemSegmentada[i] = rgb; 
             } 
        }
        isAnotation = true;
        return img;
    }
}