package segmentacaodeimagem;

import java.sql.*;
import org.sqlite.JDBC;

/**
 *
 * @author hiago
 */
public class SQLiteJDBC {
    
    private Connection conexao;
    private Statement stmt;
    private String banco;

    public SQLiteJDBC() {
        this.banco = "jdbc:sqlite:seg.db";
    }
    
    /**
     * já criei o banco, então nem precisa mais disso aqui
     */
    private void criarTabela() {
        try {
            conexao = DriverManager.getConnection(banco);
            System.out.println("Opened database successfully");

            stmt = conexao.createStatement();
            String sql = "CREATE TABLE ANOTACAO " +
                         "(ID INTEGER PRIMARY KEY  AUTOINCREMENT   NOT NULL," +
                         " TAG TEXT NOT NULL, " + 
                         " REGIAO INT NOT NULL, " +
                         " ID_IMG_FK INT NOT NULL)";
            
            String sql2 = "CREATE TABLE IMG " +
                         "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +                        
                         " PATHIMG CHAR(50) NOT NULL)";
            
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            
            stmt.close();
            conexao.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    
    public void inserirDados(String path, String tag, int regiao) {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection("jdbc:sqlite:seg.db");
            conexao.setAutoCommit(false);
            System.out.println("Opened database successfully");

            //InserirImg(path);
            int img_id = SelecionarImg(path);
            
            stmt = conexao.createStatement();
            String sql = "INSERT INTO ANOTACAO (TAG,REGIAO,ID_IMG_FK) VALUES ("+tag+", "+regiao+", "+img_id+");"; 
            stmt.executeUpdate(sql);


            stmt.close();
            conexao.commit();
            conexao.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Records created successfully");
      }
    
    public int SelecionarImg(String pathImg){
        conexao = null;
        stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          conexao = DriverManager.getConnection(banco);
          conexao.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = conexao.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM IMG WHERE PATHIMG = '"+pathImg+"';" );
          
          //Se selecionar algo retorno o id
          //se não encontrar nada retorno -1
          if(rs.getFetchSize() > 0){
            int id = 0;
            while ( rs.next() ) {
               id = rs.getInt("id");
               String path = rs.getString("PATHIMG");
               System.out.println( "ID = " + id );
               System.out.println( "Path = " + path );
            }    
            return id;
          } else{
            rs.close();
            stmt.close();
            conexao.close();
            return -1;
          }
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return -1;
        }
    }
    
    public void InserirImg(String path){
        conexao = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = conexao.createStatement();
            String sql = "INSERT INTO IMG (PATHIMG) VALUES ('"+path+"');"; 
            //sql = "INSERT INTO IMG (PATHIMG) VALUES ('TESTE1233');";
            stmt.executeUpdate(sql);
          
            stmt.close();
            conexao.commit();
            conexao.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    public void ListarImg(){
        conexao = null;
        stmt = null;
        try {
          conexao = DriverManager.getConnection(banco);
          conexao.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = conexao.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM IMG;" );
          while ( rs.next() ) {
             int id = rs.getInt("id");
             String  path = rs.getString("PATHIMG");
             System.out.println( "ID = " + id );
             System.out.println( "PATHIMG = " + path );
             System.out.println();
          }
          rs.close();
          stmt.close();
          conexao.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
    

