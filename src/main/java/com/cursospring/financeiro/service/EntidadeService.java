package com.cursospring.financeiro.service;

import com.cursospring.financeiro.exception.NegocioException;
import com.cursospring.financeiro.model.Entidade;
import com.cursospring.financeiro.repository.EntidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EntidadeService {

	@Autowired
	private EntidadeRepository entidadeRepository;
	
	public void salvar(Entidade entidade) {
		entidadeRepository.save(entidade);
	}

	public void remover(Long codigo) {

		try {
			entidadeRepository.deleteById(codigo);
		}catch (DataIntegrityViolationException exception){
			throw new NegocioException("Não é permitido excluir entidades com títulos associados, exclua primeiro os " +
								"títulos da entidade e tente novamente.");
		}


	}
	
}
