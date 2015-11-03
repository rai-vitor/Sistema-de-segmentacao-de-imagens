/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segmentacaodeimagem;

import java.sql.*;

/**
 *
 * @author hiago
 */
public class SQLiteJDBC {
    
    private Connection conexao;
    private Statement parametro;
    
    public void criarTabela() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection("jdbc:sqlite:seg.db");
            System.out.println("Opened database successfully");

            parametro = conexao.createStatement();
            String sql = "CREATE TABLE ANOTACAO " +
                         "(ID INT PRIMARY KEY  AUTOINCREMENT   NOT NULL," +
                         " TAG           TEXT    NOT NULL, " + 
                         " REGIAO            INT     NOT NULL, " +
                         " ID_IMG_FK INT NOT NULL)";
            
            parametro.executeUpdate(sql);
            
            String sql2 = "CREATE TABLE IMG " +
                         "(ID INT PRIMARY KEY AUTOINCREMENT NOT NULL," +                        
                         " PATHIMG CHAR(50) NOT NULL)";
            
            parametro.close();
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

            InserirImg(path);
            int img_id = SelecionarImg(path);
            
            parametro = conexao.createStatement();
            String sql = "INSERT INTO ANOTACAO (TAG,REGIAO,ID_IMG_FK) VALUES ("+tag+", "+regiao+", "+img_id+");"; 
            parametro.executeUpdate(sql);


            parametro.close();
            conexao.commit();
            conexao.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Records created successfully");
      }
    
    public int SelecionarImg(String pathImg){
        //aqui vai fazer um select
        //select id  where PATHIMG like %pathImg%
        return 1;
    }
    
    public void InserirImg(String path){
        
    }
}
    

