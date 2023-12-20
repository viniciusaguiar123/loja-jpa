package br.com.alura.loja.modelo.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        Pedido pedido = pedidoDAO.buscarPedidoComCliente(1L);
        em.close();

        System.out.println(pedido.getCliente().getNome());

        //ProdutoDAO produtoDao = new ProdutoDAO(em);
        // ClienteDAO clienteDao = new ClienteDAO(em);

        //Produto produto = produtoDao.buscarPorId(1L);
        //Produto produto2 = produtoDao.buscarPorId(2L);
        //Produto produto3 = produtoDao.buscarPorId(3L);
        //Cliente cliente = clienteDao.buscarPorId(1L);

        //em.getTransaction().begin();

        //Pedido pedido = new Pedido(cliente);
        //pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        //pedido.adicionarItem(new ItemPedido(40, pedido, produto2));
        //        Pedido pedido2 = new Pedido(cliente);
        //        pedido2.adicionarItem(new ItemPedido(2, pedido2, produto3));
        //
        //        PedidoDAO pedidoDao = new PedidoDAO(em);
        //        pedidoDao.cadastrar(pedido);
        //        pedidoDao.cadastrar(pedido2);
        //
        //        em.getTransaction().commit();
        //
        //        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        //        System.out.println("VALOR TOTAL: " +totalVendido);
        //
        //        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        //        relatorio.forEach(System.out::println);

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
        Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

        Cliente cliente = new Cliente("Rodrigo", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        ClienteDAO clienteDao = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }

}
