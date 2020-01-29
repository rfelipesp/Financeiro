package com.cursospring.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursospring.financeiro.model.Entidade;
import com.cursospring.financeiro.repository.EntidadeRepository;

@Service
public class EntidadeService {

	@Autowired
	private EntidadeRepository entidadeRepository;
	
	public void salvar(Entidade entidade) {
		entidadeRepository.save(entidade);
	}

	public void remover(Long codigo) {
		entidadeRepository.deleteById(codigo);
	}
	
}
