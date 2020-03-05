package br.com.bolao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bolao.services.ConviteService;

@Controller
@RequestMapping("/convites")
public class ConviteController {


	
	@Autowired
	ConviteService service;
	
	@GetMapping("/{id}")
	public ModelAndView ativar(@PathVariable Integer id){		
		String pagina = service.validar(id);		
		
		ModelAndView modelAndView = new ModelAndView(pagina);
		modelAndView.addObject("idConvite", id);		
		
		return modelAndView;
	}
	
	@PostMapping
	public String cadastrar(String login, String senha, Integer idConvite){	
		return service.cadastrar(login, senha, idConvite);
	}
}
