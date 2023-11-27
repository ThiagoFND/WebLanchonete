package br.com.salgados.vendas.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salgados.vendas.dto.ProdutosDTO;
import br.com.salgados.vendas.exceptions.ResourceNotFoundException;
import br.com.salgados.vendas.mapper.DozerMapper;
import br.com.salgados.vendas.model.Produtos;
import br.com.salgados.vendas.repositories.ProdutosRepository;

@Service
public class ProdutosService {

    private Logger logger = Logger.getLogger(ProdutosService.class.getName());

    @Autowired
	private ProdutosRepository produtosRepository;
    
	public List<ProdutosDTO> findAll() {

		logger.info("Finding all produtos!");

		return DozerMapper.parseListObjects(produtosRepository.findAll(), ProdutosDTO.class);
	}

	public ProdutosDTO findById(String id) {

		logger.info("Finding one produtos!");

		var entity = produtosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, ProdutosDTO.class);
	}

	public ProdutosDTO create(ProdutosDTO produtos) {

		logger.info("Creating one produtos!");
		var entity = DozerMapper.parseObject(produtos, Produtos.class);
		var vo = DozerMapper.parseObject(produtosRepository.save(entity), ProdutosDTO.class);
		return vo;
	}

	public ProdutosDTO update(ProdutosDTO produtos) {

		logger.info("Updating one produtos!");

		var entity = produtosRepository.findById(produtos.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setData(produtos.getData());
		entity.setFalta(produtos.getFalta());
		entity.setProduto(produtos.getProduto());

		var vo = DozerMapper.parseObject(produtosRepository.save(entity), ProdutosDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one produtos!");

		var entity = produtosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		produtosRepository.delete(entity);
	}
}
