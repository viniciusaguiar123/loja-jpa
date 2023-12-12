package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }
}
