package br.com.salgados.vendas.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salgados.vendas.dto.GastosDTO;
import br.com.salgados.vendas.exceptions.ResourceNotFoundException;
import br.com.salgados.vendas.mapper.DozerMapper;
import br.com.salgados.vendas.model.Gastos;
import br.com.salgados.vendas.repositories.GastosRepository;

@Service
public class GastosService {

    private Logger logger = Logger.getLogger(GastosService.class.getName());

    @Autowired
	private GastosRepository gastosRepository;
    
	public List<GastosDTO> findAll() {

		logger.info("Finding all gastos!");

		return DozerMapper.parseListObjects(gastosRepository.findAll(), GastosDTO.class);
	}

	public GastosDTO findById(String id) {

		logger.info("Finding one gastos!");

		var entity = gastosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, GastosDTO.class);
	}

	public GastosDTO create(GastosDTO gastos) {

		logger.info("Creating one gastos!");
		var entity = DozerMapper.parseObject(gastos, Gastos.class);
		var vo = DozerMapper.parseObject(gastosRepository.save(entity), GastosDTO.class);
		return vo;
	}

	public GastosDTO update(GastosDTO gastos) {

		logger.info("Updating one gastos!");

		var entity = gastosRepository.findById(gastos.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setData(gastos.getData());
		entity.setDescricao(gastos.getDescricao());
		entity.setValor(gastos.getValor());

		var vo = DozerMapper.parseObject(gastosRepository.save(entity), GastosDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one gastos!");

		var entity = gastosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		gastosRepository.delete(entity);
	}
}
