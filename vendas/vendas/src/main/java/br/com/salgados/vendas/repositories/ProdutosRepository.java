package br.com.salgados.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.salgados.vendas.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, String> {

}
