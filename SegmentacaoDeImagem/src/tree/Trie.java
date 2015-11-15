package tree;

public class Trie{

    private final TrieNode root;

    public Trie(){
        root = new TrieNode(' ');
    }
    
    public void insert(String word) {
        if (search(word) == true) {
            System.out.println("palavra já inserida");
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
    
    void print(TrieNode node, String path) {
        if(this.root.childList.isEmpty()){
            System.out.println("Árvore vazia!");
            return;
        }
        if(node.getContent()!=' '){
            path += node.getContent();
        }
        if(node.getEnd()==true){
            System.out.println(path);
        }
        for(TrieNode childList : node.childList){
            print(childList, path);
            path = path.substring(0, path.length());
        }
    }
 
    public void remove(String word){
        if (search(word) == false){
            System.out.println(word +" Não está na árvore\n");
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
    
    public TrieNode getRoot(){
        return root;
    }
}       