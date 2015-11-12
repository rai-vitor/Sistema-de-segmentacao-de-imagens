package segmentacaodeimagem;
import java.util.ArrayList;

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
}
