package br.com.salgados.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.salgados.vendas.model.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, String> {

}
