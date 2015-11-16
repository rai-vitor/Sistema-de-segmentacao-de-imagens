package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private static Statement stmt;
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
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM IMG WHERE PATHIMG = '" + pathImg + "';");

          //Se selecionar algo retorna o id
            //se não encontrar nada retorno -1
            int id = -1;
            while (rs.next()) {
                id = rs.getInt("ID_IMG");
            }

            rs.close();
            stmt.close();
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
     * @return Retorna o id da imagem, se existir. Caso contrário retorna -1.
     */
    public Imagem SelecionarImg(String pathImg, ListAnotacoes<Anotacao> notes) {
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ANOTACAO INNER JOIN IMG ON ANOTACAO.ID_IMG_FK = IMG.ID_IMG WHERE PATHIMG = '" + pathImg + "';");

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
            stmt.close();
            conexao.close();
            return img;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Seleciona uma imagem no banco de dados e retorna o id dela.
     *
     * @param tag
     * @param s     
     */
    public void buscarPath(String tag, ArrayList<String> s) {
        conexao = null;
        pstmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            String sql = "SELECT * FROM ANOTACAO INNER JOIN IMG ON ANOTACAO.ID_IMG_FK = IMG.ID_IMG WHERE TAG = '"+tag+"';";
            pstmt = conexao.prepareStatement(sql);
            
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
        conexao = null;
        pstmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            String sql = "INSERT INTO IMG (PATHIMG, BLUR, RADIUS, SIZE) VALUES (?, ?, ?, ?);";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, img.getPath());
            pstmt.setDouble(2, img.getBlur());
            pstmt.setInt(3, img.getRadius());
            pstmt.setInt(4, img.getSize());
            pstmt.execute();
            pstmt.close();
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
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            String sql = "INSERT INTO ANOTACAO (TAG,REGIAO,ID_IMG_FK) VALUES ('" + tag + "', '" + regiao + "', '" + imgId + "');";
            stmt.executeUpdate(sql);

            conexao.commit();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Seleciona todas as imagens no banco.
     *
     * @return retorna o caminho e o id das imagens no banco.
     */
    public Map<Integer, String> ListarImg() {
        conexao = null;
        stmt = null;
        Map<Integer, String> mapImg = new HashMap<>();

        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM IMG;");

            while (rs.next()) {
                int id = rs.getInt("ID_IMG");
                String path = rs.getString("PATHIMG");
                System.out.println("ID = " + id);
                System.out.println("PATHIMG = " + path);
                System.out.println();
                mapImg.put(id, path);
            }
            rs.close();
            stmt.close();
            conexao.close();
            return mapImg;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Lista todas as anotações do banco.
     *
     * @return lista com todas as anotações.
     */
    public ArrayList<Anotacao> ListarAnotacoes() {
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);
            ArrayList<Anotacao> listaAnotacao = new ArrayList<>();
            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ANOTACAO INNER JOIN IMG ON ANOTACAO.ID_IMG_FK = IMG.ID_IMG;");
            while (rs.next()) {
                String tag = rs.getString("TAG");
                int regiao = rs.getInt("REGIAO");
                String pathImg = rs.getString("PATHIMG");
                Anotacao note = new Anotacao(pathImg, tag, regiao);
                listaAnotacao.add(note);
            }
            rs.close();
            stmt.close();
            conexao.close();
            return listaAnotacao;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Conta a quantidade de imagens no banco.
     *
     * @return Retorna a quantidade de imagens no banco.
     */
    public int CountImg() {
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            //stmt = conexao.createStatement();
            pstmt = conexao.prepareStatement("SELECT COUNT (*) AS TAMANHO FROM IMG;");

            ResultSet rs = pstmt.executeQuery();
            //ResultSet rs = stmt.executeQuery("SELECT COUNT (*) AS TAMANHO FROM IMG;");
            int tamanho = rs.getInt("TAMANHO");

            rs.close();
            pstmt.close();
            //stmt.close();
            conexao.close();
            return tamanho;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return 0;
    }

    /**
     * Deleta uma imagem no banco.
     *
     * @param idImg - id da imagem a ser deletada.
     */
    public void DeletarImg(int idImg) {
        try {
            conexao = DriverManager.getConnection(banco);
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            String sql = "DELETE FROM ANOTACAO WHERE ID_IMG_FK = '" + idImg + "';";
            stmt.executeUpdate(sql);

            conexao.commit();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
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
            conexao.setAutoCommit(false);

            stmt = conexao.createStatement();
            String sql = "DELETE FROM ANOTACAO WHERE TAG = '" + note.getTag() + "' AND REGIAO = '" + note.getRegiao() + "' AND ID_IMG_FK = '" + idImg + "';";
            stmt.executeUpdate(sql);

            conexao.commit();
            stmt.close();
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
        conexao = null;
        stmt = null;
        try {
            conexao = DriverManager.getConnection(banco);
            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ANOTACAO;");
/*
            tags.clear();
            imgs.clear();*/
            Trie t = new Trie();
            while (rs.next()) {
                
                String tag = rs.getString("TAG");
                /*
                int regiao = rs.getInt("REGIAO");
                String pathImg = rs.getString("PATHIMG");
                Anotacao note = new Anotacao(pathImg, tag, regiao);
                tags.add(note);
                
                if (!imgs.contains(pathImg)) { // para não ter paths repetidos
                    double blur = rs.getDouble("BLUR");
                    int radius = rs.getInt("RADIUS");
                    int size = rs.getInt("SIZE");
                    Imagem img = new Imagem(pathImg,blur, radius, size);
                    imgs.add(img);
                }
                */
                t.insert(tag); // add na arvore trie
            }

            rs.close();
            stmt.close();
            conexao.close();
            return t;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
