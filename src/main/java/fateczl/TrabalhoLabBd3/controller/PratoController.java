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
	
	@GetMapping("/pratos")
	public String pratos(Model model, @RequestParam Map<String, String> params) {
		String acao = params.get("acao");
		String pratoId = params.get("prato_id");
		List<Prato> pratos = pratoService.findAll();
		Prato prato = new Prato();

		if (pratoId != null && !pratoId.isEmpty()) {
			Optional<Prato> pratoOpt = pratoService.findById(pratoId);
			if (pratoOpt.isPresent()) {				
				if (acao.equals("excluir")) {
					pratoService.excluir(pratoOpt.get());
					prato = null;
				} else if (acao.equals("editar")) {
					prato = pratoOpt.get();
				}
			}
		} 
		model.addAttribute("pratos", pratos);
		model.addAttribute("prato", prato);
		return "pratos";
	}
	
	
	@PostMapping("/novoPrato")
	public String novoPrato(@RequestParam Map<String, String> params, ModelMap model, HttpServletResponse response) {
		Prato prato = new Prato();
		prato.setNome(params.get("nome"));
		prato.setTamanho_porcao(Integer.parseInt(params.get("tamanho_porcao")));
		prato.setTipo(tipoService.findById(Long.valueOf(params.get("tipo_id"))).get());
		pratoService.save(prato);
		List<Prato> pratos = pratoService.findAll();
		model.addAttribute("pratos", pratos);
		model.addAttribute("prato", null);
		return "pratos";
	}
}