package segmentacaodeimagem;

public class Anotacao {
    private String pathImg;
    private String tag;
    private int regiao;
        
    public Anotacao(String pathImg_, String tag_, int regiao_){
        this.pathImg = pathImg_;
        this.tag = tag_;
        this.regiao = regiao_;
    }
    
    /**
     * Salva os dados no banco de dados
     */
    public void Salvar(){
        SQLiteJDBC banco = SQLiteJDBC.getInstance();
        banco.InserirDados(pathImg, tag, regiao);
    }
    
    public void Remover(){
        SQLiteJDBC banco = SQLiteJDBC.getInstance();
        banco.Deletar(this);
    }
    
    @Override
    public String toString(){
        System.out.println("tag: "+tag);
        System.out.println("região "+regiao);
        return "";
    }

    /**
     * @return the pathImg
     */
    public String getPathImg() {
        return pathImg;
    }

    /**
     * @param pathImg the pathImg to set
     */
    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the regiao
     */
    public int getRegiao() {
        return regiao;
    }

    /**
     * @param regiao the regiao to set
     */
    public void setRegiao(int regiao) {
        this.regiao = regiao;
    }
    
}
