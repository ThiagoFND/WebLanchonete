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

import br.com.salgados.vendas.dto.VendasDTO;
import br.com.salgados.vendas.services.VendasService;

//endpoints permitidos somente pra empresa
@RestController
@RequestMapping("api/v1/dados/vendas")
public class VendasController {

	@Autowired
	private VendasService vendasService;
	
	 // endpoint adicionar dados
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public VendasDTO create(@RequestBody VendasDTO vendas) {
		return vendasService.create(vendas);
	}

    // endpoint pra listar dados
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendasDTO> findAll() {
		return vendasService.findAll();
	}
	
    @GetMapping("/findid/{id}")
    public ResponseEntity<VendasDTO> findById(@PathVariable String id) {
    	VendasDTO setorDTO = vendasService.findById(id);
        return ResponseEntity.ok(setorDTO);
    }

    // endpoint pra deletar dados
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
    	vendasService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
