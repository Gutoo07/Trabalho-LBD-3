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

import fateczl.TrabalhoLabBd3.model.Tipo;
import fateczl.TrabalhoLabBd3.service.TipoService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TipoController {
	@Autowired
	TipoService tipoService;
	
	@GetMapping("/tipos")
	public String tipos(Model model, @RequestParam Map<String, String> params) {
		String acao = params.get("acao");
		String tipo_id = params.get("tipo_id");
		List<Tipo> tipos = tipoService.findAll();
		Tipo tipo = new Tipo();
		
		if (tipo_id != null && !tipo_id.isEmpty()) {
			Optional<Tipo> tipoOpt = tipoService.findById(Long.valueOf(tipo_id));
			if (tipoOpt.isPresent()) {
				if (acao.equalsIgnoreCase("Excluir")) {
					tipoService.excluir(tipoOpt.get());
				}
				if (acao.equalsIgnoreCase("Editar")) {
					tipo = tipoOpt.get();
				}
			}
		}
		model.addAttribute("tipos", tipos);
		model.addAttribute("tipo", tipo);
		return "redirect:/admin";
	}
	
	@PostMapping("/crudTipo")
	public String crudTipo(@RequestParam Map<String, String> params, ModelMap model, HttpServletResponse response) {
		Tipo tipo = new Tipo();
		String tipo_id = params.get("tipo_id");
		String nome = params.get("nome");
		String acao = params.get("acao");
			
		if (acao.equalsIgnoreCase("Inserir") || acao.equalsIgnoreCase("Atualizar")) {
			tipo.setNome(nome);
		}
		if (acao.equalsIgnoreCase("Atualizar") || acao.equalsIgnoreCase("Excluir") ) {
			tipo.setId(Long.valueOf(tipo_id));
		}
		String erro = "";
		try {
			if (acao.equalsIgnoreCase("Inserir") || acao.equalsIgnoreCase("Atualizar")) {
				tipoService.save(tipo);
			}
			if (acao.equalsIgnoreCase("Excluir")) {
				tipoService.excluir(tipo);
			}
			if (acao.equalsIgnoreCase("Buscar")) {
				Optional<Tipo> tipoOpt = tipoService.findById(Long.valueOf(tipo_id));
				if (tipoOpt.isPresent()) {
					tipo = tipoOpt.get();
				}
			}
		} catch (Exception e) {
			erro = e.getMessage();
		} finally {
			List<Tipo> tipos = tipoService.findAll();
			model.addAttribute("erro", erro);
			model.addAttribute("tipo", tipo);
			model.addAttribute("tipos", tipos);
		}		
		return "redirect:/admin";
	}
}
