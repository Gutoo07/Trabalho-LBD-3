package fateczl.TrabalhoLabBd3.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fateczl.TrabalhoLabBd3.model.Tipo;
import fateczl.TrabalhoLabBd3.service.TipoService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TipoController {
	@Autowired
	TipoService tipoService;
	
	@PostMapping("/novoTipo")
	public String novoTipo(@RequestParam Map<String, String> params, ModelMap model, HttpServletResponse response) {
		Tipo tipo = new Tipo();
		tipo.setNome(params.get("nome"));
		tipoService.save(tipo);
		return "tipos";
	}
}
