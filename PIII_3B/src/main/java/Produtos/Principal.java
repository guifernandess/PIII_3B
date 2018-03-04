//acho que ficaria melhor em uma interfaze g, do que no console, vai ficar um pouco mais confuso
//essa classe serve como se fosse a tela do usuario com um menu simples, apague este comentario antes de enviar gente
package Produtos;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Maia
 */
public class Principal {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("O que você quer fazer?");
        int opc = 0;
        boolean on = false;
        while (on == false) {
            System.out.println("1 - Adicionar um produto");
            System.out.println("2 - Criar uma nova categoria de produto");
            System.out.println("7 - Sair");
            
            opc = console.nextInt();
            switch (opc){
                case 1: {
                    System.out.print("Digite o nome do novo produto: ");
                    String nome = console.next();
                    System.out.print("Digite a descrição do produto: ");
                    String descr = console.next();
                    System.out.print("Digite o preço de compra: ");
                    double precCompra = console.nextDouble();
                    System.out.print("Digite o preço de venda: ");
                    double precVenda = console.nextDouble();
                    System.out.print("Agora, por ultimo, digite a quantidade: ");
                    int qtdPro = console.nextInt();
                    
                    Produto p = new Produto();
                    p.setNome(nome); p.setDescricao(descr); p.setPrecoCompra(precCompra);
                    p.setPrecoVenda(precVenda); p.setQuantidade(qtdPro);
                    evento ev = new evento();
                try {
                    ev.inserir(p);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                   

                } break;
                case 2: {
                    System.out.print("Digite o nome da nova categoria: ");
                    String categ = console.next();
                    evento ev = new evento();
                try {
                    ev.inserirCategoria(categ);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                } break;
                case 7: {
                    on = true;
                } break;
            }
        }

    }
}
