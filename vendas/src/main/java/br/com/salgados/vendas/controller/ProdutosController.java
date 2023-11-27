package br.com.salgados.vendas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salgados.vendas.dto.ProdutosDTO;
import br.com.salgados.vendas.services.ProdutosService;

//endpoints permitidos somente pra empresa
@RestController
@RequestMapping("api/v1/dados/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosService produtosService;
	
	 // endpoint adicionar dados
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutosDTO create(@RequestBody ProdutosDTO produtos) {
		return produtosService.create(produtos);
	}

    // endpoint pra listar dados
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProdutosDTO> findAll() {
		return produtosService.findAll();
	}
	
    @GetMapping("/findid/{id}")
    public ResponseEntity<ProdutosDTO> findById(@PathVariable String id) {
    	ProdutosDTO setorDTO = produtosService.findById(id);
        return ResponseEntity.ok(setorDTO);
    }

    // endpoint pra deletar dados
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
    	produtosService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
