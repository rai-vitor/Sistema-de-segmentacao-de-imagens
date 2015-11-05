package segmentacaodeimagem;

public class Anotacao {
    private String pathImg;
    private String tag;
    private int regiao;
    
    public Anotacao(String tag_, int regiao_){
        this.tag = tag_;
        this.regiao = regiao_;
    }
    
    public Anotacao(String pathImg_, String tag_, int regiao_){
        this.pathImg = pathImg_;
        this.tag = tag_;
        this.regiao = regiao_;
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
