package segmentacaodeimagem;

/**
 * Uma classe que realiza operções sobre as regiões de uma imagem segmentada.
 * @author Hiago Miguel & Rai Vitor
 */
public class Regiao {
    
    /* Coordenadas de um pixel */
    private int coordinateX;
    private int coordinateY;
    
     /**
     * Define as coordenadas x e y de um pixel, com base  as coordenadas obtidas
     * por MouseEvent e pela largura da imagem.
     * @param coordinateX Coordenada x de um pixel.
     * @param coordinateY Coordenada y de um pixel.
     * @param largura Largura da imagem.
     */
    public Regiao(int coordinateX, int coordinateY, int largura) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY*largura;
    }
    
     /**
     * Destaca a região selecionada na imagem.
     * @param img Imagem que contém a região do pixel a ser destacado.
     */
    public void destacarRegiao(Imagem img) {
        int pixel = coordinateX + coordinateY;
        //Se a região do pixel a ser destacada já estiver adicionada no array pixelRegion, então esta não é adicionada.
         if(!img.getPixelRegion().contains(img.getMapaDaRegiaoSegmentada()[pixel])){
            img.getPixelRegion().add(img.getMapaDaRegiaoSegmentada()[pixel]);
            //O destaque da região só será feito se a região adicionada for nova.
            Filtro.EscurecerPixel(img);         
        }
    }    
      
    /**
     * Associa uma anotação (tag) a uma região da imagem.
     * @param tag Tag a ser associada a uma região.
     * @param notes ArrayList do tipo Anotação que contém as notas (tags) 
     * de cada região da imagem.
     * @param img
     */
    public void AssocTagRegiao(String tag, ListAnotacoes<Anotacao> notes, Imagem img) {
        for(int i = 0; i < img.getPixelRegion().size(); i++) {
            Anotacao note = new Anotacao(ConvertImage.getCaminhoDaImagem(), tag,img.getPixelRegion().get(i));
            notes.add(note);
        }
        System.out.println(notes);
    }
}
