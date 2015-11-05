package segmentacaodeimagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que persiste os dados no banco de dados Sqlite
 */
public class SQLiteJDBC {
    private static SQLiteJDBC instancia;
    private static Connection conexao;
    private static Statement stmt;
    private static String banco = "jdbc:sqlite:seg.db";

    /**
     * Construtor que não pode ser instanciado
     * Utilizar getInstance para ter uma instancia da classe
     */
    private SQLiteJDBC() {}
    
    /**
     * Método para garantir o padrão Singleton
     * @return Instancia do banco de dados
     */
    public static synchronized SQLiteJDBC getInstance(){
        if(instancia == null){
            instancia = new SQLiteJDBC();
        }
        return instancia;
    }
    
    /**
     * Cria o banco de dados e suas tabelas sqlite;
     */
    private void CriarTabela() {
        try {
            conexao = DriverManager.getConnection(banco);

            stmt = conexao.createStatement();
            String sql = "CREATE TABLE ANOTACAO " +
                         "(ID_ANOTACAO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                         " TAG TEXT NOT NULL, " + 
                         " REGIAO INT NOT NULL, " +
                         " ID_IMG_FK INT NOT NULL)";
            
            String sql2 = "CREATE TABLE IMG " +
                         "(ID_IMG INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +                        
                         " PATHIMG CHAR(50) NOT NULL)";
            
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            
            stmt.close();
            conexao.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    /**
     * Método que controla a inserção dos dados no banco 
     * @param path - Caminho da imagem
     * @param tag - Anotação
     * @param regiao - Região da imagem que será associada a uma tag
     */
    public void InserirDados(String path, String tag, int regiao) {
            int imgId = SelecionarImg(path);
            if(imgId > -1){
                InserirAnotacao(imgId, tag, regiao);
            } else{
                InserirImg(path);
                InserirDados(path, tag, regiao);
            }
      }
    
    /**
     * Seleciona uma imagem no banco de dados e retorna o id dela;
     * @param pathImg - Caminho da imagem a ser procurada
     * @return Retorna o id da imagem, se existir. Caso contrário retorna -1
     */
    public int SelecionarImg(String pathImg){
        conexao = null;
        stmt = null;
        try {
          conexao = DriverManager.getConnection(banco);
          conexao.setAutoCommit(false);

          stmt = conexao.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM IMG WHERE PATHIMG = '"+pathImg+"';" );
          
          //Se selecionar algo retorna o id
          //se não encontrar nada retorno -1
            int id = -1;
            while ( rs.next() ) {
               id = rs.getInt("ID_IMG");
            }    
            
            rs.close();
            stmt.close();
            conexao.close();
            return id;
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return -1;
        }
    }
    
    /**
     * Insere uma imagem no banco de dados
     * @param path - Caminho da imagem a ser inserido no banco
     */
    private void InserirImg(String path){
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            String sql = "INSERT INTO IMG (PATHIMG) VALUES ('"+path+"');"; 
            stmt.executeUpdate(sql);
          
            conexao.commit();
            stmt.close();
            conexao.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /**
     * Insere uma anotação no banco
     * @param imgId - Id da imagem da anotação
     * @param tag - Anotação
     * @param regiao - Região da imagem atrelada a tag
     */
    private void InserirAnotacao(int imgId, String tag, int regiao){
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            String sql = "INSERT INTO ANOTACAO (TAG,REGIAO,ID_IMG_FK) VALUES ('"+tag+"', '"+regiao+"', '"+imgId+"');"; 
            stmt.executeUpdate(sql);
          
            conexao.commit();
            stmt.close();
            conexao.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /**
     * Seleciona todas as imagens no banco
     * @return retorna o caminho e o id das imagens no bano
     */
    public Map<Integer, String> ListarImg(){
        conexao = null;
        stmt = null;
        Map<Integer,String> mapImg = new HashMap<>();
        
        try {
          conexao = DriverManager.getConnection(banco);
          conexao.setAutoCommit(false);

          stmt = conexao.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM IMG;" );
          
          while ( rs.next() ) {
             int id = rs.getInt("ID_IMG");
             String  path = rs.getString("PATHIMG");
             System.out.println( "ID = " + id );
             System.out.println( "PATHIMG = " + path );
             System.out.println();
             mapImg.put(id, path);
          }
          rs.close();
          stmt.close();
          conexao.close();
          return mapImg;
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return null;
    }
    
    /**
     * Lista todas as anotações do banco
     * @return lista com todas as anotações
     */
    public ArrayList<Anotacao> ListarAnotacoes(){
        conexao = null;
        stmt = null;
        try {
          conexao = DriverManager.getConnection(banco);
          conexao.setAutoCommit(false);
          ArrayList<Anotacao> listaAnotacao = new ArrayList<>();
          stmt = conexao.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM ANOTACAO INNER JOIN IMG ON ANOTACAO.ID_IMG_FK = IMG.ID_IMG;" );
          while ( rs.next() ) {
             String  tag = rs.getString("TAG");
             int regiao = rs.getInt("REGIAO");
             String pathImg = rs.getString("PATHIMG");
             Anotacao note = new Anotacao(pathImg, tag, regiao);
             listaAnotacao.add(note);
          }
          rs.close();
          stmt.close();
          conexao.close();
          return listaAnotacao;
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return null;
    }
    
    /**
     * Conta a quantidade de imagens no banco
     * @return quantidade de imagens no banco
     */
    public static int CountImg(){
        conexao = null;
        stmt = null;
        try {
          conexao = DriverManager.getConnection(banco);
          conexao.setAutoCommit(false);
          stmt = conexao.createStatement();
          
          ResultSet rs = stmt.executeQuery("SELECT COUNT (*) AS TAMANHO FROM IMG;");
          int tamanho = rs.getInt("TAMANHO");
         
          rs.close();
          stmt.close();
          conexao.close();
          return tamanho;
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return 0;
    }
    
    /**
     * Deleta uma imagem no banco
     * @param idImg id da imagem a ser deletada
     */
    public void DeletarImg(int idImg){
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            String sql = "DELETE FROM ANOTACAO WHERE ID_IMG_FK = '"+idImg+"';"; 
            stmt.executeUpdate(sql);
          
            conexao.commit();
            stmt.close();
            conexao.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /**
     * Deleta uma anotação no banco
     * @param note anotação a ser deletada
     */
    public void DeletarAnotacao(Anotacao note){
        int idImg = SelecionarImg(note.getPathImg());
        if(idImg == -1)
            return;
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            String sql = "DELETE FROM ANOTACAO WHERE TAG = '"+note.getTag()+"' AND REGIAO = '"+note.getRegiao()+"' AND ID_IMG_FK = '"+idImg+"';"; 
            stmt.executeUpdate(sql);
          
            conexao.commit();
            stmt.close();
            conexao.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
    

