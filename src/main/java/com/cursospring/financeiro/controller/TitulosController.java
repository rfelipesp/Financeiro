package com.cursospring.financeiro.controller;

import com.cursospring.financeiro.model.*;
import com.cursospring.financeiro.repository.EntidadeRepository;
import com.cursospring.financeiro.repository.TipoDePagamentoRepository;
import com.cursospring.financeiro.repository.TituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cursospring.financeiro.service.TituloService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/titulos")
public class TitulosController {
	
private static final String INDEX = "titulo/CadastrarTitulo";

	@Autowired
	private EntidadeRepository entidadeRepository;
	
	@Autowired
	private TituloService tituloService;

	@Autowired
	private TipoDePagamentoRepository tipoDePagamentoRepository;

	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping(value = "/novo")
	public ModelAndView novo(Titulo titulo) {
		ModelAndView modelAndView = new ModelAndView(INDEX);
		modelAndView.addObject("listaDeEntidades", entidadeRepository.findAll());
		modelAndView.addObject("todosOsTipos", Tipo.values());
		modelAndView.addObject("todasAsSituacoes", Situacao.values());
		modelAndView.addObject("tiposDePagamento", tipoDePagamentoRepository.findAll());
		return modelAndView;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Titulo titulo, BindingResult result, RedirectAttributes attributes){

		if(result.hasErrors()){
			return novo(titulo);
		}

		tituloService.salvar(titulo);
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return new ModelAndView("redirect:/titulos/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(Titulo titulo){
		ModelAndView modelAndView = new ModelAndView("titulo/PesquisarTitulo");
		modelAndView.addObject("listaDeEntidades", entidadeRepository.findAll());
		modelAndView.addObject("todosOsTipos", Tipo.values());

		String descricao = titulo.getDescricao() == null ? "" : titulo.getDescricao().toUpperCase();
		modelAndView.addObject("titulos", tituloRepository.findByFiltro(descricao, titulo.getEntidade(), titulo.getTipo()));

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> novoTipoDePagamento(@RequestBody @Valid TipoDePagamento tipoDePagamento,
															   BindingResult result){

		if (result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}

		tipoDePagamento = tipoDePagamentoRepository.save(tipoDePagamento);
		return ResponseEntity.ok(tipoDePagamento);
	}

}
