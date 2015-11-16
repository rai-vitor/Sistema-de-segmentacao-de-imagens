package segmentacaodeimagem;

import database.DataBase;

/**
 * Classe que realiza a persistência das anotações no banco.
 * @author Hiago Miguel & Rai Vitor.
 */
public class Anotacao {
    private String pathImg;
    private String tag;
    private int regiao;
        
    /**
     * Construtor 
     * @param pathImg_ - Caminho da imagem
     * @param tag_ - Anotação
     * @param regiao_ - Região da imagem
     */
    public Anotacao(String pathImg_, String tag_, int regiao_){
        this.pathImg = pathImg_;
        this.tag = tag_;
        this.regiao = regiao_;
    }
    
    /**
     * Salva os dados no banco de dados.
     */
    public void Salvar(){
        DataBase banco = DataBase.getInstance();
        banco.InserirDados(pathImg, tag, regiao);
    }
    
    /**
     * Remove a anotação do banco.
     */
    public void Remover(){
        DataBase banco = DataBase.getInstance();
        banco.DeletarAnotacao(this);
    }
    
    /**
     * Sobrescreve o método toString().     
     */
    @Override
    public String toString(){
        System.out.println("tag: "+tag);
        System.out.println("região "+regiao);
        return "";
    }

    /**
     * Retorna o caminho da imagem.
     * @return Retorna o caminho da imagem.
     */
    public String getPathImg() {
        return pathImg;
    }

    /**
     * Define o caminho da imagem.
     * @param pathImg Caminho da imagem a ser definido.
     */
    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    /**
     * Retorna uma tag associada a uma região.
     * @return Retorna uma tag.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Define uma tag.
     * @param tag Tag a ser definida.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Retorna a região.
     * @return Retorna uma região.
     */
    public int getRegiao() {
        return regiao;
    }

    /**
     * Define a região.
     * @param regiao Região a ser definida.
     */
    public void setRegiao(int regiao) {
        this.regiao = regiao;
    }
    
}
