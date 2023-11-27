package br.com.salgados.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.salgados.vendas.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	UserDetails findByLogin(String login);
}
