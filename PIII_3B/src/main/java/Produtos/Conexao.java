/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author guilherme.rocha
 */
public class Conexao {

    private Connection obterConexao() throws ClassNotFoundException, SQLException {
        // Driver MySql
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test", "planejamento", "ccash01");
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE PRODUTO SET "
                    + "nome = ?,descricao =?, preco_compra=?, preco_venda=?, quantidade=?"
                    + "FROM PRODUTO WHERE ID = ?");
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

    public void excluir() throws ClassNotFoundException, SQLException {
        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM PRODUTO WHERE id = ? ")) {
            stmt.setString(1, "id");
            stmt.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
