package com.cursospring.financeiro.controller;

import javax.validation.Valid;

import com.cursospring.financeiro.dto.EntidadeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursospring.financeiro.model.Entidade;
import com.cursospring.financeiro.repository.EntidadeRepository;
import com.cursospring.financeiro.service.EntidadeService;

import java.util.List;

@Controller
@RequestMapping("/entidades")
public class EntidadesController {
	
	private static final String INDEX = "entidade/CadastrarEntidade";
	
	@Autowired
	private EntidadeService entidadeService;
	
	@Autowired
	private EntidadeRepository entidadeRepository;
	
	@GetMapping("/novo")
	public String novo(Entidade entidade) {
		return INDEX;
	}
	
	@PostMapping("/novo")
	public String salvar(@Valid Entidade entidade, BindingResult result, RedirectAttributes attributes)	 {
		
		if(result.hasErrors()) {
			// Mostrar a mensagem de erro.
			return novo(entidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Entidade salva com sucesso");
		entidadeService.salvar(entidade);
		
		return "redirect:/entidades/novo";
	}
	
	@GetMapping
	public ModelAndView pesquisar(Entidade entidade) {
		
		
		ModelAndView modelAndView = new ModelAndView("entidade/PesquisarEntidade");	
		
		if(entidade.getNome() == null) {
			modelAndView.addObject("entidades", entidadeRepository.findByNomeQuery(entidade.getNome()));
		}else {
			modelAndView.addObject("entidades", entidadeRepository.findByNomeContainingIgnoreCase(entidade.getNome()));
		}
		
		return modelAndView;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Entidade entidade) {
		
		ModelAndView modelAndView = new ModelAndView(INDEX);
		modelAndView.addObject(entidade);
		return modelAndView;
	}
	

	@PostMapping(value = "/{codigo}")
	public String excluir(@PathVariable("codigo") Long codigo) {
		entidadeService.remover(codigo);
		return "redirect:/entidades"; 
	}

	@GetMapping("/filtro")
	public @ResponseBody List<EntidadeDto> filtradas(String nome){
		return entidadeRepository.findEntidadeDtoByFiltro(nome.toUpperCase());
	}


}
