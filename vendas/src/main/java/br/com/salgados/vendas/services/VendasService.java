package br.com.salgados.vendas.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salgados.vendas.dto.VendasDTO;
import br.com.salgados.vendas.exceptions.ResourceNotFoundException;
import br.com.salgados.vendas.mapper.DozerMapper;
import br.com.salgados.vendas.model.Vendas;
import br.com.salgados.vendas.repositories.VendasRepository;


@Service
public class VendasService {


    private Logger logger = Logger.getLogger(VendasService.class.getName());

    @Autowired
	private VendasRepository vendasRepository;
    
	public List<VendasDTO> findAll() {

		logger.info("Finding all vendas!");

		return DozerMapper.parseListObjects(vendasRepository.findAll(), VendasDTO.class);
	}

	public VendasDTO findById(String id) {

		logger.info("Finding one vendas!");

		var entity = vendasRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, VendasDTO.class);
	}

	public VendasDTO create(VendasDTO vendas) {

		logger.info("Creating one vendas!");
		var entity = DozerMapper.parseObject(vendas, Vendas.class);
		var vo = DozerMapper.parseObject(vendasRepository.save(entity), VendasDTO.class);
		return vo;
	}

	public VendasDTO update(VendasDTO vendas) {

		logger.info("Updating one vendas!");

		var entity = vendasRepository.findById(vendas.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setCartao(vendas.getCartao());
		entity.setData(vendas.getData());
		entity.setDinheiro(vendas.getDinheiro());
		entity.setPix(vendas.getPix());
		
		var vo = DozerMapper.parseObject(vendasRepository.save(entity), VendasDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one vendas!");

		var entity = vendasRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		vendasRepository.delete(entity);
	}
}
