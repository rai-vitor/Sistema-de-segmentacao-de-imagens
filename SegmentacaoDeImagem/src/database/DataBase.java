package database;

import java.sql.*;
import java.util.ArrayList;
import segmentacaodeimagem.Anotacao;
import segmentacaodeimagem.Imagem;
import segmentacaodeimagem.ListAnotacoes;
import tree.Trie;

/**
 * Classe que persiste os dados no banco de dados Sqlite.
 *
 * @author Hiago Miguel & Rai Vitor.
 */
public class DataBase {

    private static DataBase instancia;
    private static Connection conexao;
    //private static Statement stmt;
    private static PreparedStatement pstmt;
    private static String banco;

    /**
     * Construtor que não pode ser instanciado. Utilizar getInstance para ter
     * uma instancia da classe.
     */
    private DataBase() {
        banco = "jdbc:sqlite:seg.db";
    }

    /**
     * Método para garantir o padrão Singleton.
     *
     * @return Instancia do banco de dados
     */
    public static synchronized DataBase getInstance() {
        if (instancia == null) {
            instancia = new DataBase();
        }
        return instancia;
    }

    /**
     * Método que controla a inserção dos dados no banco.
     *
     * @param path - Caminho da imagem.
     * @param tag - Anotação.
     * @param regiao - Região da imagem que será associada a uma tag.
     */
    public void InserirDados(String path, String tag, int regiao) {
        int imgId = SelecionarIdImg(path);
        InserirAnotacao(imgId, tag, regiao);
    }

    /**
     * Seleciona uma imagem no banco de dados e retorna o id dela.
     *
     * @param pathImg - Caminho da imagem a ser procurada.
     * @return Retorna o id da imagem, se existir. Caso contrário retorna -1.
     */
    public int SelecionarIdImg(String pathImg) {
        try {
            conexao = DriverManager.getConnection(banco);
            String query = "SELECT * FROM IMG WHERE PATHIMG = ?;";
            pstmt = conexao.prepareStatement(query);
            pstmt.setString(1, pathImg);
            ResultSet rs = pstmt.executeQuery();
            
            //Se selecionar algo retorna o id
            //se não encontrar nada retorno -1
            int id = -1;
            while (rs.next()) {
                id = rs.getInt("ID_IMG");
            }

            rs.close();
            pstmt.close();
            conexao.close();
            return id;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Seleciona uma imagem no banco de dados e retorna o id dela.
     *
     * @param pathImg - Caminho da imagem a ser procurada.
     * @param notes
     * @return Retorna o id da imagem, se existir. Caso contrário retorna -1.
     */
    public Imagem SelecionarImg(String pathImg, ListAnotacoes<Anotacao> notes) {
        try {
            conexao = DriverManager.getConnection(banco);
            String query = "SELECT * FROM ANOTACAO INNER JOIN IMG ON ANOTACAO.ID_IMG_FK = IMG.ID_IMG WHERE PATHIMG = ?;";
            pstmt = conexao.prepareStatement(query);
            pstmt.setString(1, pathImg);
            ResultSet rs = pstmt.executeQuery();

            Imagem img = null;
            while (rs.next()) {
                String path = rs.getString("PATHIMG");
                double blur = rs.getDouble("BLUR");
                int radius = rs.getInt("RADIUS");
                int size = rs.getInt("SIZE");
                img = new Imagem(path,blur, radius, size);
                
                String tag = rs.getString("TAG");
                int regiao = rs.getInt("REGIAO");
                Anotacao note = new Anotacao(path, tag, regiao);
                notes.add(note);
            }

            rs.close();
            pstmt.close();
            conexao.close();
            return img;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
    
    /**
     * @param tag
     * @param s     
     */
    public void buscarPath(String tag, ArrayList<String> s) {
        try {
            conexao = DriverManager.getConnection(banco);
            String query = "SELECT * FROM ANOTACAO INNER JOIN IMG ON ANOTACAO.ID_IMG_FK = IMG.ID_IMG WHERE TAG = ?;";
            pstmt = conexao.prepareStatement(query);
            pstmt.setString(1, tag);
            
            ResultSet rs = pstmt.executeQuery();
            String path;
            while(rs.next()){
                path = rs.getString("PATHIMG");
                if(!s.contains(path)){
                    s.add(path);
                }
            }
            
            pstmt.close();
            conexao.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } 
    }

    /**
     * Insere uma imagem no banco de dados.
     *
     * @param img
     */
    public void InserirImg(Imagem img) {
        try {
            conexao = DriverManager.getConnection(banco);
            String sql = "INSERT INTO IMG (PATHIMG, BLUR, RADIUS, SIZE) VALUES (?, ?, ?, ?);";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, img.getPath());
            pstmt.setDouble(2, img.getBlur());
            pstmt.setInt(3, img.getRadius());
            pstmt.setInt(4, img.getSize());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        }
    }

    /**
     * Insere uma anotação no banco.
     *
     * @param imgId - Id da imagem da anotação.
     * @param tag - Anotação.
     * @param regiao - Região da imagem atrelada a tag
     */
    private void InserirAnotacao(int imgId, String tag, int regiao) {
        try {
            conexao = DriverManager.getConnection(banco);
            String sql = "INSERT INTO ANOTACAO (TAG,REGIAO,ID_IMG_FK) VALUES (?, ?, ?);";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, tag);
            pstmt.setInt(2, regiao);
            pstmt.setInt(3, imgId);
            pstmt.execute();
            
            pstmt.close();
            conexao.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Conta a quantidade de imagens no banco.
     * @return Retorna a quantidade de imagens no banco.
     */
    public int CountImg() {
        try {
            conexao = DriverManager.getConnection(banco);
            pstmt = conexao.prepareStatement("SELECT COUNT (*) AS TAMANHO FROM IMG;");
            ResultSet rs = pstmt.executeQuery();
            int tamanho = rs.getInt("TAMANHO");

            rs.close();
            pstmt.close();
            conexao.close();
            return tamanho;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return 0;
    }

    /**
     * Deleta uma anotação no banco.
     *
     * @param note - anotação a ser deletada.
     */
    public void DeletarAnotacao(Anotacao note) {
        int idImg = SelecionarIdImg(note.getPathImg());
        if (idImg == -1) {
            return;
        }
        try {
            conexao = DriverManager.getConnection(banco);
            String sql = "DELETE FROM ANOTACAO WHERE TAG = ? AND ID_IMG_FK = ?;";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, note.getTag());
            pstmt.setInt(2, idImg);
            pstmt.execute();

            pstmt.close();
            conexao.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Busca no banco de dados todas as anotações
     * @return Arvore Trie com todas as anotações.
     */
    public Trie getAnotacoes() {
        try {
            conexao = DriverManager.getConnection(banco);
            pstmt = conexao.prepareStatement("SELECT * FROM ANOTACAO;");
            ResultSet rs = pstmt.executeQuery();

            Trie t = new Trie();
            while (rs.next()) {
                String tag = rs.getString("TAG");
                t.insert(tag); // adiciona na árvore trie
            }

            rs.close();
            pstmt.close();
            conexao.close();
            return t;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}