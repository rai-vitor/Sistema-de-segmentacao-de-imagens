package segmentacaodeimagem;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private static Map<Integer,String> tagImg = new HashMap<>();
    
    
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
        pixelsDaImagemSegmentada2 = new ArrayList<>();
        CopiarArray();
        pixelRegion = new ArrayList<>();
        return seg;
    }       
    
    private static void CopiarArray(){
        for(int i=0; i<pixelsDaImagemSegmentada.length; i++){
            pixelsDaImagemSegmentada2.add(pixelsDaImagemSegmentada[i]);
        }
    }
    
    public static void RestaurarImg(int id){
        for (int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            pixelsDaImagemSegmentada[i] = pixelsDaImagemSegmentada2.get(i);
        }
        if(id == 1){
            pixelRegion.clear();
        }
    }
    
    public static ArrayList<Anotacao> AssocTagRegiao(String tag, ArrayList<Anotacao> notes) {
        for(int i = 0; i < pixelRegion.size(); i++) {
            Anotacao note = new Anotacao(notes.size(), ConvertImage.getCaminhoDaImagem(), tag, pixelRegion.get(i));
            notes.add(note);
            tagImg.put(pixelRegion.get(i), tag);
        }
        
        return notes;
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
    public static void setCoordenadas ( int x, int y, int largura) {
        coordinateX = x;//largura
        coordinateY = y*largura;//altura
    }
    
    public static void getPixel(ImageInformation img) {
        int pixel = coordinateX + coordinateY;
        //se já tiver adicionado, não adiciona
        if(!pixelRegion.contains(mapaDaRegiaoSegmentada[pixel])){
            pixelRegion.add(mapaDaRegiaoSegmentada[pixel]);
            //só se eu add uma nova região que eu chamo a função
            darkenPixels(img, pixelRegion);
        }
    }
    
    /**
     * Com base nos values de um Map o método retorna um Set com as keys desses values
     */
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet()
                  .stream()
                  .filter(entry -> Objects.equals(entry.getValue(), value))
                  .map(Map.Entry::getKey)
                  .collect(Collectors.toSet());
    }
    
    /**
     * Possivelmente vai ter o nome mudado
     * Recebe uma tag e destaca as regiões na imagem
     * @param img 
     * @param tag 
     */
    public static void Selecionar(ImageInformation img, String tag){
        Object[] arrayKeys = getKeysByValue(tagImg, tag).toArray();
        ArrayList<Integer> arrayKey = new ArrayList<>();
        for (int i = 0; i < arrayKeys.length; i++) {
            arrayKey.add((int)arrayKeys[i]);
        }
        darkenPixels(img, arrayKey);
    }
    
    //muda cor dos pixels, para destacar a região selecionada
    public static ImageInformation darkenPixels(ImageInformation img, ArrayList<Integer> pixelRegion) {
        Color c;
        int red;
        int green;
        int blue;
        int rgb;
        
        //Toda vez eu restauro img para a original. Assim garanto que somente as outras regiões serão apagadas.
        RestaurarImg(0);
        for(int i = 0; i < pixelsDaImagemSegmentada.length; i++) {
            if(!pixelRegion.contains(mapaDaRegiaoSegmentada[i])) {
                 c = new Color(pixelsDaImagemSegmentada[i]);
                 red = c.getRed();
                 green = c.getBlue();
                 blue = c.getBlue();
                 rgb = (((red/3)&0x0ff)<<16)|(((green/3)&0x0ff)<<8)|((blue/3)&0x0ff);
                 pixelsDaImagemSegmentada[i] = rgb; 
             } 
        }
        return img;
    }
}