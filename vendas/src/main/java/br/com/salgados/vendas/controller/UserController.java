package br.com.salgados.vendas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salgados.vendas.dto.UserDTO;
import br.com.salgados.vendas.services.UserService;

@RestController
@RequestMapping("api/v1/admin/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	 // endpoint adicionar dados
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO create(@RequestBody UserDTO user) {
		return userService.create(user);
	}

    // endpoint pra listar dados
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> findAll() {
		return userService.findAll();
	}
	
    @GetMapping("/findid/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    // endpoint pra atualizar dados
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO update(@PathVariable String id, @RequestBody UserDTO user) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'dados'
		user.setId(id);

		// Chame o serviço 'dadosService' para atualizar os dados
		UserDTO updatedDAdosd = userService.update(user);
		return updatedDAdosd;
	}

    // endpoint pra deletar dados
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}