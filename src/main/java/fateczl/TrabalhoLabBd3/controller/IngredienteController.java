package fateczl.TrabalhoLabBd3.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fateczl.TrabalhoLabBd3.model.Ingrediente;
import fateczl.TrabalhoLabBd3.service.IngredienteService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class IngredienteController {
	@Autowired
	IngredienteService ingService;
	
	@GetMapping("/ingredientes")
	public String ingredientes(Model model, @RequestParam Map<String, String> params) {
		String acao = params.get("acao");
		String id = params.get("id");
		String nome = params.get("nome");
		String apresentacao = params.get("apresentacao");
		
		Ingrediente ingrediente = new Ingrediente();
		List<Ingrediente> ingredientes = ingService.findAll();
		
		if (id != null && !id.isEmpty()) {
			Optional<Ingrediente> ingOpt = ingService.findById(Long.valueOf(id));
			if (ingOpt.isPresent()){
				if (acao.equalsIgnoreCase("Excluir")) {
					ingService.excluir(ingOpt.get());
				}
				if (acao.equalsIgnoreCase("Editar")) {
					ingrediente = ingOpt.get();
				}
			}
		}
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("ingrediente", ingrediente);
		return "ingredientes";
	}
	
	@PostMapping("/crudIngrediente")
	public String crudIngrediente(@RequestParam Map<String, String> params, ModelMap model, HttpServletResponse response) {
		Ingrediente ingrediente = new Ingrediente();
		String id = params.get("id");
		String nome = params.get("nome");
		String apresentacao = params.get("apresentacao");
		
		String acao = params.get("acao");
		String erro = "";		
		
		if (acao.equalsIgnoreCase("Inserir") || acao.equalsIgnoreCase("Atualizar")) {
			ingrediente.setNome(nome);
			ingrediente.setApresentacao(apresentacao);
		}
		if (acao.equalsIgnoreCase("Atualizar") || acao.equalsIgnoreCase("Excluir")) {
			if (id != null && !id.isEmpty()) {
				ingrediente.setId(Long.valueOf(id));
			}
		}
		try {			
			if (acao.equalsIgnoreCase("Inserir")) {
				if (id != null && !id.isEmpty()) {
					erro = "Um ingrediente com esse ID ja existe.";
				} else {
					ingService.save(ingrediente);
					ingrediente = null;
				}
			}
			if (acao.equalsIgnoreCase("Atualizar")) {
				if (ingrediente.getId() != null) {
					if (ingService.findById(ingrediente.getId()).isPresent()) {
						ingService.save(ingrediente);
						ingrediente = null;
					} else {
						erro = "Ingrediente inexistente";
					}
				} else {
					erro = "ID em branco";
				}
			}
			if (acao.equalsIgnoreCase("Excluir")) {
				if (ingrediente.getId() != null) {
					if (ingService.findById(ingrediente.getId()).isPresent()) {
						ingService.excluir(ingrediente);
						ingrediente = null;
					} else {
						erro = "Ingrediente inexistente";
					}
				} else {
					erro = "ID em branco";
				}
			}				
			if (acao.equalsIgnoreCase("Buscar")) {
				if (nome != null && !nome.isEmpty()) {
					Optional<Ingrediente> ingOpt = ingService.findByNome(nome);
					if (ingOpt.isPresent()) {
						ingrediente = ingOpt.get();
					} else {
						erro = "Ingrediente inexistente";
					}
				} else {
					erro = "Nome do ingrediente nao informado";
				}				
			}
			System.err.println(erro);
		} catch (Exception e) {
			erro = e.getMessage();
		} finally {
			List<Ingrediente> ingredientes = ingService.findAll();
			model.addAttribute("ingrediente", ingrediente);
			model.addAttribute("ingredientes", ingredientes);
			model.addAttribute("erro", erro);
		}
		return "ingredientes";
	}
}
