package tree;
import java.util.*;

/**
 * Uma classe que representa o nó da árvore trie.
 * @author Hiago Miguel e Rai Vitor
 */
class TrieNode{
    private char content; 
    private boolean isEnd; 
    private int cont;  
    public ArrayList<TrieNode> childList; 
    
    /**
     * Construtor da Classe TrieNode
     * @param c Caractere do nó.
     */

    public TrieNode(char c){
        childList = new ArrayList<>();
        isEnd = false;
        content = c;
        cont = 0;
    }  

    /**
     * Sub nó da árvore Trie.
     * @param c Caractere do nó.
     * @return Sub nó.
     */
    public TrieNode subNode(char c){
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.content == c)
                    return eachChild;
        return null;
    }
    
    /**
     * Retorna o conteúdo do nó;
     * @return Caractere.
     */
    public char getContent(){
        return this.content;
    }
    
    /**
     * Define se é o fim da árvore ou não.
     * @param b 
     */
    public void setEnd(boolean b){
        this.isEnd = b;
    }
    
    /**
     * Retorna se é o fim da árvore ou não.
     * @return Se é o fim da árvore ou não.
     */
    public boolean getEnd(){
        return this.isEnd;
    }
    
    /**
     * Incrementa o número total de nós da árvore.
     * @param n Valor a ser incrementado.
     */
    public void addCont(int n){
        this.cont += n;
    }
    
    /**
     * Retorna o número total de nós da árvore.
     * @return Número total de nós da árvore.
     */
    public int getCont(){
        return this.cont;
    }
}