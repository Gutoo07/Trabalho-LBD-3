package fateczl.TrabalhoLabBd3.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.service.PratoService;
import fateczl.TrabalhoLabBd3.service.TipoService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PratoController {
	@Autowired
	PratoService pratoService;
	@Autowired
	TipoService tipoService;
	
	@PostMapping("/novoPrato")
	public String novoPrato(@RequestParam Map<String, String> params, ModelMap model, HttpServletResponse response) {
		Prato prato = new Prato();
		prato.setNome(params.get("nome"));
		prato.setTamanho_porcao(Integer.parseInt(params.get("tamanho_porcao")));
		prato.setTipo(tipoService.findById(Long.valueOf(params.get("tipo_id"))).get());
		pratoService.save(prato);
		return "pratos";
	}
}
