package tree;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ESSA CLASSE NÃO VAI EXISTIR FOI SÓ PARA TESTAR
 */
public class TrieMain{

    public static void main(String[] args){          

        Scanner scan = new Scanner(System.in);
        Trie t = new Trie(); 
        ArrayList<String> s = new ArrayList<>();
        char ch;

        do{
            System.out.println("\nTrie Operações\n");
            System.out.println("1 - Inserir ");
            System.out.println("2 - Deletar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Imprimir");

            int choice = scan.nextInt();            

            switch (choice){
                case 1 : 
                    System.out.println("Digite uma string que deseja inserir");
                    t.insert( scan.next() );                     
                    break;                          
                case 2 : 
                    System.out.println("DIgite uma string que deseja deletar");
                    try{
                        t.remove( scan.next() ); 
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage()+" nao encontrado ");
                    }
                    break;                         
                case 3 : 
                    System.out.println("Digite uma string que deseja buscar");
                    System.out.println("Resultado da busca: "+ t.search( scan.next() ));
                    break;                                          
                case 4: 
                    t.print(t.getRoot(), "", s);
                    System.out.println(s.toString());
                    break;            
                default: 
                    System.out.println("Numero invalido \n ");
                    break;   
            }
            
            System.out.println("\nDeseja continuar? (S ou N) \n");
            
            ch = scan.next().charAt(0);                        
        } while (ch == 's'|| ch == 'S');               
    }
}