package br.com.salgados.vendas.dto.v1;

import br.com.salgados.vendas.model.UserRole;

public record RegisterDTO(String nome, String email, String cpfcnpj, String login, String password, UserRole role) {
}
