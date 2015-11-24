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
     * @param word Palavra a ser inserida.
     */
    public void insert(String word) {
        if (search(word) == true) {
            //System.out.println("palavra já inserida");
            return;
        }
        TrieNode current = root; 
        for (char ch : word.toCharArray() ) {
            TrieNode child = current.subNode(ch);
            if (child != null) {
                current = child;
            } else {
                 current.childList.add(new TrieNode(ch));
                 current = current.subNode(ch);
            }
            current.addCount(1);
        }
        current.setEnd(true);
    }

    /**
     * Busca uma palavra na Trie.
     * @param word Palavra a ser buscada.
     * @return Verdadeiro ou Falso.
     */
    public boolean search(String word){
        TrieNode current = root;  
        for (char ch : word.toCharArray() ){
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
        }      
        return current.getEnd();
    }
    
    /**
     * Imprime a árvore Trie.
     * @param node Nó a partir do qual a árvore será impressa.
     * @param path 
     * @param s 
     */
    public void print(TrieNode node, String path, ArrayList<String> s) {
        if(this.root.childList.isEmpty()){
            //System.out.println("Árvore vazia!");
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
 
    /**
     * Remove uma palavra da árvore.
     * @param word Palavra a ser removida.
     */
    public void remove(String word){
        if (search(word) == false){
            //System.out.println(word +" Não está na árvore\n");
            return;
        }             

        TrieNode current = root;

        for (char ch : word.toCharArray()){
            TrieNode child = current.subNode(ch);
            if (child.getCount() == 1){
                current.childList.remove(child);
                return;
            } else{
                child.addCount(-1);
                current = child;
            }
        }

        current.setEnd(false);
    }
    
    /**
     * Retorna a raíz da árvore.
     * @return  Raíz da árvore.
     */
    public TrieNode getRoot(){
        return root;
    }
}       