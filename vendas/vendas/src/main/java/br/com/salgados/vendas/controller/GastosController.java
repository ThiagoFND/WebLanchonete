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

import br.com.salgados.vendas.dto.GastosDTO;
import br.com.salgados.vendas.services.GastosService;

//endpoints permitidos somente pra empresa
@RestController
@RequestMapping("api/v1/dados/gastos")
public class GastosController {

	@Autowired
	private GastosService gastosService;
	
	 // endpoint adicionar dados
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public GastosDTO create(@RequestBody GastosDTO gastos) {
		return gastosService.create(gastos);
	}

    // endpoint pra listar dados
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GastosDTO> findAll() {
		return gastosService.findAll();
	}
	
    @GetMapping("/findid/{id}")
    public ResponseEntity<GastosDTO> findById(@PathVariable String id) {
    	GastosDTO setorDTO = gastosService.findById(id);
        return ResponseEntity.ok(setorDTO);
    }

    // endpoint pra deletar dados
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
    	gastosService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
