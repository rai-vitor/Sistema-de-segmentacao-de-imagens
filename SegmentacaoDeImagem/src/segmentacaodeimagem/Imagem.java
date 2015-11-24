package segmentacaodeimagem;

import database.DataBase;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Uma classe que realiza a segmentação de uma imagem, bem como o mapa de rótulos da mesma.
 * @author Hiago Miguel e Rai Vitor.
 */
public class Imagem {      
    private int[] variacaoGray;
    /* Define a intensidade da tonalidade cinza inicial a ser utilizada*/
    private int defGrey;
    /* Campos para realizar a segmentação da imagem. */
    private final String path;    
    private double blur;
    private int radius;
    private int size;
    /* Array que contém as regiões dos pixels selecionados */
    private ArrayList<Integer> pixelRegion;
    /* Array que contém os pixels RGB da imagem segmentada não modificados. */
    private ArrayList<Integer> pixelsDaImagemSegmentadaBckp;
    /* Objeto do tipo ImageInformation que contém dados da imagem segmentada. */
    ImageInformation seg;
    
    /**
     * Construtor da classe Imagem. Inicializa os parâmetros abaixo.
     * @param path Local de armazenamento da imagem segmentada.
     * @param blur valor do blur
     * @param radius valor do radius
     * @param size valor do size
     */
    
    public Imagem(String path, double blur, int radius, int size){
        this.path = path;
        this.blur = blur;
        this.radius = radius;
        this.size = size;
    }
    
    /**
     * Segmenta uma dada imagem de acordo com os parâmetros abaixo.
     * 
     * @param blur Nível de desfoque (blur) para suavizar arestas da imagem.
     * @param radius Principal parâmetro do algoritmo Mean Shift. Sua mudança é bastante sensível ao resultado da segmentação.
     * @param size Tamanho mínimo das regiões obtidas na segmentação.
     */
    public void segmentar(double blur, int radius, int size) { 
        this.blur = blur;
        this.radius = radius;
        this.size = size;
        seg = ImageSegmentation.performSegmentation(getPath(), blur, radius, size);
        variacaoGray = new int[seg.getTotalRegions()];        
        defGrey = 255/seg.getTotalRegions();
        setPixelsDaImagemSegmentadaBckp(new ArrayList<>());
        CopiarArray();
        setPixelRegion(new ArrayList<>());
    }           
    
    /**
     * Realiza uma cópia da imagem segmentada.
     */
    private void CopiarArray(){
        for(int i=0; i<getPixelsDaImagemSegmentada().length; i++){
            getPixelsDaImagemSegmentadaBckp().add(getPixelsDaImagemSegmentada()[i]);
        }
    }
    
    /**
     * Restaura a imagem segmentada com o brilho original dela
     * @param flag se ela for 1 limpa 'pixelRegion'
     */
    public void RestaurarImg(int flag){
        for (int i = 0; i < getPixelsDaImagemSegmentada().length; i++) {
            getPixelsDaImagemSegmentada()[i] = getPixelsDaImagemSegmentadaBckp().get(i);
        }
        if(flag == 1){
            getPixelRegion().clear();
        }
    }
    
    /**
     * Cria o mapa de rótulos de uma dada imagem segmentada.
     * 
     * A imagem é construída a partir da informação das regiões obtidas através do mapa de rótulos 
     * (método getSegmentedImageMap) e do número total de regiões (método getTotalRegions).
     * 
    */
    public void GerarMapaRotulos() {        
        /* Recebe a tonalidade cinza de uma regiao.*/
        int gray = 0;
        /* Pixel RGB de tonalidade cinza a ser mapeado para o array pixelsDaImagemSegmentada. */
        int rgb = 0;        
        
        GapGray(getTotalRegioes());
        
        for(int i = 0; i < getPixelsDaImagemSegmentada().length; i++) {
            gray = variacaoGray[getMapaDaRegiaoSegmentada()[i]];
            rgb = ((gray&0x0ff)<<16)|((gray&0x0ff)<<8)|(gray&0x0ff);
            getPixelsDaImagemSegmentada()[i] = rgb;            
        }        
    }        
    
    /**
     * Gera espacamentos iguais entre os possiveis 255 cinzas
     * @param tamanhoRegiao - Quantidade de regioes existentes na imagem segmentada 
     */
    private void GapGray(int tamanhoRegiao){
        for(int i = 0; i < tamanhoRegiao; i++) {
            variacaoGray[i] = defGrey*i;
        }
    }
    
    /**
     * Salva a imagem no banco de dados.
     */
    public void Salvar(){
        DataBase db = DataBase.getInstance();
        db.InserirImg(this);
    }

    /**
     * Retorna o mapa da imagem segmentada.
     * @return o array de inteiros (mapa) da imagem segmenta.
     */
    public int[] getMapaDaRegiaoSegmentada() {
        return seg.getSegmentedImageMap();
    }

    /**
     * Retorna os pixels da imagem segmentada.
     * @return o array de pixels RGB da imagem segmentada.
     */
    public int[] getPixelsDaImagemSegmentada() {
        return seg.getRegionMarkedPixels();
    }

    /**
     * Retorna as regiões dos pixels selecionados.
     * @return o array das regiões dos pixels selecionados.
     */
    public ArrayList<Integer> getPixelRegion() {
        return pixelRegion;
    }
    
     /**
     * Retorna o array original, não modificado, dos pixels RGB da imagem segmentada.
     * @return o array original dos pixels RGB da imagem segmentada.
     */
    public ArrayList<Integer> getPixelsDaImagemSegmentadaBckp() {
        return pixelsDaImagemSegmentadaBckp;
    }

     /**
      * Retorna o número total de regiões da imagem segmentada.
     * @return o inteiro do total de regiões da imagem segmentada.
     */
    public int getTotalRegioes() {
        return seg.getTotalRegions();
    }
    
     /**
      * Retorna a imagem segmentada.
     * @return a imagem segmentada.
     */
    public BufferedImage getImgSegmentada() {
        return seg.getRegionMarkedImage();
    }

    /**
     * Retorna o local de armazenamento da imagem.
     * @return a string que contém o local de armazenamento da imagem.
     */
    public String getPath() {
        return path;
    }

    /**
     * Retorna o valor do blur.
     * @return o blur para a segmentação da imagem.
     */
    public double getBlur() {
        return blur;
    }

    /**
     * Retorna o valor do radius
     * @return o radius para a segmentação da imagem.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Retorna o valor size (Tamanho mínimo das regiões obtidas na segmentação.). 
     * @return o size para a segmentação da imagem.
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Define o array pixelRegion.
     * @param aPixelRegion o array das regiões dos pixels selecionados a ser definido.
     */
    public void setPixelRegion(ArrayList<Integer> aPixelRegion) {
        pixelRegion = aPixelRegion;
    }

    /** Define o array pixelsDaImagemSegmentadaBckp.
     * @param aPixelsDaImagemSegmentadaBckp o array orginal dos pixels RGB da imagem segmentada a ser definido.
     */
    public void setPixelsDaImagemSegmentadaBckp(ArrayList<Integer> aPixelsDaImagemSegmentadaBckp) {
        pixelsDaImagemSegmentadaBckp = aPixelsDaImagemSegmentadaBckp;
    }
}