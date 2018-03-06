//acho que ficaria melhor em uma interfaze g, do que no console, vai ficar um pouco mais confuso
//essa classe serve como se fosse a tela do usuario com um menu simples, apague este comentario antes de enviar gente
package Produtos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vitoria Cristina
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
            System.out.println("3 - Excluir um produto");
            System.out.println("4 - Pesquisar um produto");
            System.out.println("5 - Atualizar um produto");
            System.out.println("7 - Sair");

            opc = console.nextInt();
            switch (opc) {
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
                    p.setNome(nome);
                    p.setDescricao(descr);
                    p.setPrecoCompra(precCompra);
                    p.setPrecoVenda(precVenda);
                    p.setQuantidade(qtdPro);
                    evento ev = new evento();
                    try {
                        ev.inserir(p);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;
                case 2: {
                    System.out.print("Digite o nome da nova categoria: ");
                    String categ = console.next();
                    evento ev = new evento();
                    try {
                        ev.inserirCategoria(categ);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 3: {
                    System.out.println("Digite o nome do produto á ser excluido: ");
                    String descr = console.next();
                    evento ev = new evento();
                    try {
                        ev.excluirProduto(descr);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case 4: {
                    System.out.println("Digite o nome do produto á ser Pesquisado: ");
                    String descr = console.next();
                    evento ev = new evento();
                    try {
                        List<Produto> lista = new ArrayList<Produto>();
                        lista = ev.pesquisar(descr);

                        for (int i = 0; i < lista.size(); i++) {
                            Produto p = new Produto();
                            p = lista.get(i);
                            String nomeProduto = p.getNome();
                            long Id = p.getId();
                            System.out.println("IdProduto: " + Id);
                            System.out.println("Produto: " + nomeProduto);

                        }

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                }
                case 5: {
                    System.out.println("Digite o id do produto a ser atualizado: ");
                    int id = console.nextInt();
                    evento ev = new evento();
                    Produto p = new Produto();
                    try {
                        p = ev.Obter(id);
                        System.out.println("Digite o nome do produto: ");
                        String Nome = console.next();
                        System.out.println("Digite a descrição do produto: ");
                        String descricao = console.next();
                        System.out.println("Digite o preço de compra do produto: ");
                        double precoCompra = console.nextDouble();
                        System.out.println("Digite o preço de venda do produto: ");
                        double precoVenda = console.nextDouble();
                        System.out.println("Digite a quantide do produto: ");
                        int quantidade = console.nextInt();
                        
                        p.setNome(Nome);
                        p.setDescricao(descricao);
                        p.setPrecoCompra(precoCompra);
                        p.setPrecoVenda(precoVenda);
                        p.setQuantidade(quantidade);

                        ev.AtualizaProduto(p);
                        System.out.println("Produto Atualizado com sucesso");

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case 7: {
                    on = true;
                }
                break;
            }
        }

    }
}
