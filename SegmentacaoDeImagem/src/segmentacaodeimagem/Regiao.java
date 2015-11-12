/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segmentacaodeimagem;

/**
 *
 * @author Rai
 */
public class Regiao {
    
    private int coordinateX;
    private int coordinateY;
    
     /**
     * Define as coordenadas x e y de um pixel, com base  as coordenadas obtidas
     * por MouseEvent e pela largura da imagem.
     * @param coordinateX
     * @param coordinateY
     * @param largura Largura da imagem.
     */
    public Regiao(int coordinateX, int coordinateY, int largura) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY*largura;
    }
    
     /**
     * Destaca a região selecionada na imagem.
     * @param img
     */
    public void destacarRegiao(Imagem img) {
        int pixel = coordinateX + coordinateY;
        //se já tiver adicionado, não adiciona        
         if(!img.getPixelRegion().contains(img.getMapaDaRegiaoSegmentada()[pixel])){
            img.getPixelRegion().add(img.getMapaDaRegiaoSegmentada()[pixel]);
            //só se eu add uma nova região que eu chamo a função
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
