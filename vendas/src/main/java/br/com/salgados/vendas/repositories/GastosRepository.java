package br.com.salgados.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.salgados.vendas.model.Gastos;

public interface GastosRepository extends JpaRepository<Gastos, String> {

}
