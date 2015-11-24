package tree;
import java.util.ArrayList;

public class Trie{

    private final TrieNode root;

    /**
     * Construtor da Classe Trie.
     * Inicia a Trie com um nó vazio.
     */
    public Trie(){
        root = new TrieNode(' ');
    }
    
    /**
     * Insere uma palavra na Trie.
     * @param palavra Palavra a ser inserida.
     */
    public void Inserir(String palavra) {
        if (Buscar(palavra) == true) {
            return;
        }
        TrieNode atual = root; 
        for (char ch : palavra.toCharArray() ) {
            TrieNode child = atual.subNode(ch);
            if (child != null) {
                atual = child;
            } else {
                 atual.childList.add(new TrieNode(ch));
                 atual = atual.subNode(ch);
            }
            atual.addCont(1);
        }
        atual.setEnd(true);
    }

    /**
     * Busca uma palavra na Trie.
     * @param palavra Palavra a ser buscada.
     * @return Verdadeiro ou Falso.
     */
    public boolean Buscar(String palavra){
        TrieNode atual = root;  
        for (char ch : palavra.toCharArray() ){
            if (atual.subNode(ch) == null)
                return false;
            else
                atual = atual.subNode(ch);
        }      
        return atual.getEnd();
    }
 
    /**
     * Remove uma palavra da árvore.
     * @param palavra Palavra a ser removida.
     */
    public void Remover(String palavra){
        if (Buscar(palavra) == false){
            return;
        }             

        TrieNode atual = root;

        for (char ch : palavra.toCharArray()){
            TrieNode child = atual.subNode(ch);
            if (child.getCont() == 1){
                atual.childList.remove(child);
                return;
            } else{
                child.addCont(-1);
                atual = child;
            }
        }

        atual.setEnd(false);
    }
    
    /**
     * Retorna a raíz da árvore.
     * @return  Raíz da árvore.
     */
    public TrieNode getRoot(){
        return root;
    }
    
    /**
     * Imprime a árvore Trie em um array
     * @param node Nó a partir do qual a árvore será impressa.
     * @param path Caminho inicial da arvore
     * @param s Array que recebe o resultado final de cada busca
     */
    public void print(TrieNode node, String path, ArrayList<String> s) {
        if(this.root.childList.isEmpty()){
            return;
        }
        if(node.getContent()!=' '){
            path += node.getContent();
        }
        if(node.getEnd()==true){
            s.add(path);
        }
        for(TrieNode childList : node.childList){
            print(childList, path, s);
            path = path.substring(0, path.length());
        }
    }
}       