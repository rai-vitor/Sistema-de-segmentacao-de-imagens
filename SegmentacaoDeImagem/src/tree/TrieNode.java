package tree;
import java.util.*;

class TrieNode{
    private char content; 
    private boolean isEnd; 
    private int count;  
    public ArrayList<TrieNode> childList; 

    public TrieNode(char c){
        childList = new ArrayList<TrieNode>();
        isEnd = false;
        content = c;
        count = 0;
    }  

    public TrieNode subNode(char c){
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.content == c)
                    return eachChild;
        return null;
    }
    
    public char getContent(){
        return this.content;
    }
    
    public void setEnd(boolean b){
        this.isEnd = b;
    }
    
    public boolean getEnd(){
        return this.isEnd;
    }
    
    public void addCount(int n){
        this.count += n;
    }
    
    public int getCount(){
        return this.count;
    }
}

