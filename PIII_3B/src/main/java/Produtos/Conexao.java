/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author guilherme.rocha
 */
public class Conexao {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        // Driver MySql
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/produtobd", "root", "");
        return conn;
    }

    public void adicionar(Produto p) throws ClassNotFoundException, SQLException {
        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO PRODUTO (nome, descricao, preco_compra, preco_venda, quantidade)"
                        + "VALUES (?,?,?,?,?) ")) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPrecoCompra());
            stmt.setDouble(4, p.getPrecoVenda());
            stmt.setInt(5, p.getQuantidade());

            int status = stmt.executeUpdate();
            System.out.println("Status: " + status);

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void adicionarCategoria(String categ) throws ClassNotFoundException, SQLException {
        try {
            Connection conn = obterConexao();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CATEGORIA (nome)"
                    + "VALUES (?)");
            stmt.setString(1, categ);
            int status = stmt.executeUpdate();
            System.out.println("Status: " + status);

            conn.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void atualizar(Produto P) throws ClassNotFoundException, SQLException {
        try {
            Connection conn = obterConexao();
            PreparedStatement stmt = conn.prepareStatement("UPDATE PRODUTOBD.PRODUTO SET "
                    + "nome = ?, descricao = ?, preco_compra= ?, preco_venda= ?, quantidade= ?"
                    + " WHERE ID = ? ");
            stmt.setString(1, P.getNome());
            stmt.setString(2, P.getDescricao());
            stmt.setDouble(3, P.getPrecoCompra());
            stmt.setDouble(4, P.getPrecoVenda());
            stmt.setInt(5, P.getQuantidade());
            stmt.setLong(6, P.getId());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void excluir(int id) throws ClassNotFoundException, SQLException {
        try (Connection conn = obterConexao();
                
                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM PRODUTOBD.PRODUTO WHERE id = ? ")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public List<Produto> procurar(String valor) throws SQLException, Exception {

        String sql = "SELECT * FROM PRODUTOBD.PRODUTO WHERE UPPER(nome) LIKE UPPER(?) ";
        //Lista de clientes de resultado
        List<Produto> listaProdutos = null;
        //Conexão para abertura e fechamento
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenará os resultados do banco de dados
        ResultSet result = null;
        Connection conn = obterConexao();
        try {
            //Abre uma conexão com o banco de dados
            
            //Cria um statement para execução de instruções SQL
            preparedStatement = conn.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setString(1, "%" + valor + "%");
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            
            //Itera por cada item do resultado
            while (result.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (listaProdutos == null) {
                    listaProdutos = new ArrayList<Produto>();
                }
                //Cria uma instância de Cliente e popula com os valores do BD
                Produto p = new Produto();
                
                p.setId(result.getInt("id"));
                p.setNome(result.getString("nome"));
                p.setDescricao(result.getString("descricao"));
                p.setPrecoCompra(result.getDouble("preco_compra"));
                p.setPrecoVenda(result.getDouble("preco_venda"));
                p.setQuantidade(result.getInt("quantidade"));
                
                //Adiciona a instância na lista
                listaProdutos.add(p);
            }
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        //Retorna a lista de clientes do banco de dados
        return listaProdutos;        
    }
     
         public static Produto obter(Integer id)
            throws SQLException, Exception {
        //Compõe uma String de consulta que considera apenas o cliente
        //com o ID informado e que esteja ativo ("enabled" com "true")
        String sql = "SELECT * FROM PRODUTOBD.PRODUTO WHERE (id=?)";

        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenará os resultados do banco de dados
        ResultSet result = null;
        Connection conn = obterConexao();
        try {
            //Abre uma conexão com o banco de dados
            
            //Cria um statement para execução de instruções SQL
            preparedStatement = conn.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setInt(1, id);            
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            
            
            
            //Verifica se há pelo menos um resultado
            if (result.next()) {                
                //Cria uma instância de Cliente e popula com os valores do BD
                Produto p = new Produto();
                
                p.setId(result.getInt("id"));
                p.setNome(result.getString("nome"));
                p.setDescricao(result.getString("descricao"));
                p.setPrecoCompra(result.getDouble("preco_compra"));
                p.setPrecoVenda(result.getDouble("preco_venda"));
                p.setQuantidade(result.getInt("quantidade"));
                
                //Retorna o resultado
                return p;
            }            
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }

        //Se chegamos aqui, o "return" anterior não foi executado porque
        //a pesquisa não teve resultados
        //Neste caso, não há um elemento a retornar, então retornamos "null"
        return null;
    }

}
