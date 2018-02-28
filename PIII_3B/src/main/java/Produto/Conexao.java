/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produto;

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

    public void incluir() throws ClassNotFoundException, SQLException {
        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO PRODUTO (nome, descricao, preco_compra, preco_venda, quantidade)"
                        + "VALUES (?,?,?,?,?) ")) {
            stmt.setString(1, "nome");
            stmt.setString(2, "descricao");
            stmt.setString(3, "preco_compra");
            stmt.setString(4, "preco_venda");
            stmt.setString(5, "quantidade");

            int status = stmt.executeUpdate();
            System.out.println("Status: " + status);
        }
    }

    public void atualizar() throws ClassNotFoundException, SQLException {

    }

    public void excluir() throws ClassNotFoundException, SQLException {

    }

}
