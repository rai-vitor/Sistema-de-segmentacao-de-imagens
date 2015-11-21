package segmentacaodeimagem;

import database.DataBase;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import tree.Trie;

/**
 * Uma classe que realiza a busca de tags associadas as regiões das imagens e, que implementa o autocomplete.
 * @author Rai Vitor.
 */

public class Busca {
    /**
     * Destaca as regiões na imagem de acordo com a tag recebida.
     * @param img Imagem a ser aplicada o filtro de destaque.
     * @param tag Tag da região selecionada.
     * @param notes Lista com as anotações
     */
    public static void Selecionar(Imagem img, String tag, ListAnotacoes<Anotacao> notes){
        ArrayList<Integer> arrayKey = notes.ProcurarTag(tag);
        img.setPixelRegion(arrayKey);
        Filtro.EscurecerPixel(img);
    }
    
    /**
     * Busca uma imagem no banco de dados e adiciona as tags numa lista
     * @param tag
     * @param tagsModel
     * @param tags
     * @return 
     */
    public static Imagem BuscarImg(String tag, DefaultListModel tagsModel, ListAnotacoes<Anotacao> tags){
        tagsModel.clear();
        tags.clear();
        Imagem img;
        DataBase db = DataBase.getInstance();
        img = db.SelecionarImg(tag, tags);
        
        //Preenche a listaTag com as anotações daquela imagem.
        for (int i = 0; i < tags.size(); i++) {
            if(!tagsModel.contains(tags.get(i).getTag())){
                tagsModel.addElement(tags.get(i).getTag());
            }
        }
        return img;
    }
    
    /**
     * Utiliza a Trie para buscar do banco de dados as anotações associadas às imagens.
     * @return A árvore trie.
     */
    public static Trie BuscarTags(){
        Trie trie = new Trie();
        DataBase db = DataBase.getInstance();
        trie = db.getAnotacoes();
        return trie;
    }
    
    /**
     * Lista as Tags associadas a uma ou mais imagens.
     * @param tag A associão ou Tag a uma região da imagem.
     * @return A lista de imagens  associadas a Tag.
     */
    public static ArrayList<String> ListarTags(String tag){
        ArrayList<String> listaPaths = new ArrayList<>();
        DataBase db = DataBase.getInstance();
        db.buscarPath(tag, listaPaths);
        return listaPaths;
    }
}
