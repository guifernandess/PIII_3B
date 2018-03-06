/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produtos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vitoria Cristina
 */
public class evento {

    public void inserir(Produto p) throws ClassNotFoundException, SQLException {
        Conexao con = new Conexao();
        con.adicionar(p);
        System.out.println("Produto Inserido!");

    }

    public void inserirCategoria(String categ) throws ClassNotFoundException, SQLException {
        Conexao conn = new Conexao();
        conn.adicionarCategoria(categ);
        System.out.println("Categoria Inserida!");
    }

    public void excluirProduto(String p) throws ClassNotFoundException, SQLException {
        Conexao con = new Conexao();
        con.excluir();
        System.out.println("Produto excluido com sucesso! ");
    }

    public void AtualizaProduto(Produto p) throws ClassNotFoundException, SQLException {
        Conexao con = new Conexao();
        con.atualizar(p);
        System.out.println("Produto atualizado com sucesso! ");
    }

    public Produto Obter(Integer id) throws ClassNotFoundException, SQLException {
        Conexao con = new Conexao();
        Produto p = new Produto();
        
        try {
            p = con.obter(id);
        } catch (Exception ex) {
            Logger.getLogger(evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
        
        
    }

    public List<Produto> pesquisar(String p) throws ClassNotFoundException, SQLException {

        try {

            Conexao con = new Conexao();
            List<Produto> lista = new ArrayList<Produto>();

            lista = con.procurar(p);
            System.out.println("Produto atualizado com sucesso! ");

            return lista;

        } catch (Exception ex) {

            Logger.getLogger(evento.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
