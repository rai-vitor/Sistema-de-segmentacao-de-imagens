package segmentacaodeimagem;

import java.util.ArrayList;

/**
 * Classe para controlar as operações de uma lista de Anotações
 * @param <E> Tipo de item que estará na lista
 */
public class ListAnotacoes<E> extends java.util.ArrayList<E>{
    
    /**
     * Persiste todos os elementos da lista
     */
    public void Salvar(){
        for(int i = 0; i < this.size(); i++){
            Anotacao note = (Anotacao)this.get(i);
            note.Salvar();
        }
    }
    
    /**
     * Remove todos os itens da lista que tiverem a tag com o valor de entrada do método
     * @param tag - Valor que será buscado em todos os itens da lista e se ele existir será excluido
     */
    public void Remover(String tag){
        for(int i = 0; i < this.size(); i++){
            Anotacao note = (Anotacao)this.get(i);
            if(note.getTag().equals(tag)){
                note.Remover();// apaga no banco
                this.remove(i);// remove da Lista
            }
        }
    }
    
    /**
     * Procura em todos os elementos da lista que tenham uma tag igual a que foi dada de entrada
     * @param tag - Tag a ser procurada
     * @return ArrayList com todas as regiões onde foram encontradas a tag de entrada
     */
    public ArrayList<Integer> ProcurarTag(String tag){
        ArrayList<Integer> numRegioes = new ArrayList<>();
        for(int i = 0; i < this.size(); i++){
            Anotacao note = (Anotacao)this.get(i);
            if(note.getTag().contains(tag)){
                numRegioes.add(note.getRegiao());
            }
        }
        return numRegioes;
    }
    
    /**
     * Sobrescreve o método toString()
     * @return 
     */
    @Override
    public String toString(){
        for(int i = 0; i < this.size(); i++){
            Anotacao note = (Anotacao)this.get(i);
            note.toString();
        }
        return "";
    }
}
