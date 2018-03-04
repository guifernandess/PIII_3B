/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produtos;

import java.sql.SQLException;


/**
 *
 * @author Matheus Maia
 */
public class evento {
    
    public void inserir(Produto p) throws ClassNotFoundException, SQLException{
        Conexao con = new Conexao();
        con.adicionar(p);
        System.out.println("Produto Inserido!");
        
    }
    
    public void inserirCategoria(String categ) throws ClassNotFoundException, SQLException{
        Conexao conn = new Conexao();
        conn.adicionarCategoria(categ);
        System.out.println("Categoria Inserida!");
    }
}
