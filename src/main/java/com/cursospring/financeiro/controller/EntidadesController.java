package com.cursospring.financeiro.controller;

import com.cursospring.financeiro.dto.EntidadeDto;
import com.cursospring.financeiro.exception.NegocioException;
import com.cursospring.financeiro.model.Entidade;
import com.cursospring.financeiro.page.PageWrapper;
import com.cursospring.financeiro.repository.EntidadeRepository;
import com.cursospring.financeiro.service.EntidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
	public ModelAndView pesquisar(Entidade entidade, @PageableDefault(size = 2) Pageable pageable,
									HttpServletRequest httpServletRequest) {

		ModelAndView modelAndView = new ModelAndView("entidade/PesquisarEntidade");
		String nome = entidade.getNome() == null ? "%" : entidade.getNome().toUpperCase();
		PageWrapper<Entidade> pageWrapper =
				new PageWrapper<>(entidadeRepository.findByNomeQuery(nome, pageable), httpServletRequest);

		modelAndView.addObject("pagina", pageWrapper);
		return modelAndView;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Entidade entidade) {
		
		ModelAndView modelAndView = new ModelAndView(INDEX);
		modelAndView.addObject(entidade);
		return modelAndView;
	}
	

	@PostMapping(value = "/{codigo}")
	public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attributes) {

		try {
			entidadeService.remover(codigo);
			attributes.addFlashAttribute("mensagem", "Entidade excluída com sucesso");
		}catch (NegocioException exception){
			attributes.addFlashAttribute("mensagemErro", exception.getMessage());
		}


		return "redirect:/entidades";
	}

	@GetMapping("/filtro")
	public @ResponseBody List<EntidadeDto> filtradas(String nome){
		return entidadeRepository.findEntidadeDtoByFiltro(nome.toUpperCase());
	}


}
