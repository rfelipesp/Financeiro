package com.cursospring.financeiro.service;

import com.cursospring.financeiro.model.Titulo;
import com.cursospring.financeiro.repository.TituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TituloService {

	@Autowired
	private TituloRepository tituloRepository;
	
	public void salvar(Titulo titulo) {
		titulo.setDataDeEmissao(new Date());
		tituloRepository.save(titulo);
	}

	public void remover(Long codigo){
		tituloRepository.deleteById(codigo);
	}
	
}
