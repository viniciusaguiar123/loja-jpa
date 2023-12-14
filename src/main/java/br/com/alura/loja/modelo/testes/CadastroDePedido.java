package br.com.alura.loja.modelo.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Produto produto = produtoDAO.buscarPorId(1L);
        Cliente cliente = clienteDAO.buscarPorId(1L);


        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new itemPedido(10, pedido, produto));

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido);

        em.getTransaction().commit();


    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito Legal", new BigDecimal("800"), celulares);

        Cliente cliente = new Cliente("Rodrigo", "123456");

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        clienteDAO.cadastrar(cliente);
        em.getTransaction().commit();
        em.close();
    }
}
