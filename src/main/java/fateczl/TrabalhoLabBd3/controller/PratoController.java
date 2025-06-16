package fateczl.TrabalhoLabBd3.controller;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fateczl.TrabalhoLabBd3.model.Ingrediente;
import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.model.Prato_Ingrediente;
import fateczl.TrabalhoLabBd3.model.Prato_IngredienteId;
import fateczl.TrabalhoLabBd3.model.Tipo;
import fateczl.TrabalhoLabBd3.service.IngredienteService;
import fateczl.TrabalhoLabBd3.service.PedidoPratoService;
import fateczl.TrabalhoLabBd3.service.PratoIngredienteService;
import fateczl.TrabalhoLabBd3.service.PratoService;
import fateczl.TrabalhoLabBd3.service.TipoService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PratoController {
	@Autowired
	PratoService pratoService;
	@Autowired
	TipoService tipoService;
	
	
	@Autowired
	PratoIngredienteService pratoIngredienteService;
	
	@Autowired
	PedidoPratoService pedidoPratoService;
	
	@Autowired
	IngredienteService ingService;
	
	
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
		return "redirect:/admin";
	}
	
	@PostMapping("/crudPrato")
	public String crudPrato(@RequestParam Map<String, String> params,
	                        @RequestParam(required = false, name = "ingredientesIds") List<Long> ingredientesIds,
	                        ModelMap model, HttpServletResponse response) {
	    Prato prato = new Prato();
	    String prato_id = params.get("prato_id");
	    String nome = params.get("nome");
	    String tamanho_porcao = params.get("tamanho_porcao");
	    String tipo_id = params.get("tipo_id");
	    BigDecimal valor = new BigDecimal(params.get("valor"));
	    prato.setId(prato_id);
	    String acao = params.get("acao");
	    String erro = "";

	    if (acao.equalsIgnoreCase("Inserir") || acao.equalsIgnoreCase("Atualizar")) {
	        prato.setNome(nome);
	        prato.setValor(valor);
	        prato.setTamanho_porcao(Integer.parseInt(tamanho_porcao));
	        Optional<Tipo> tipo = tipoService.findById(Long.valueOf(tipo_id));
	        tipo.ifPresent(prato::setTipo);
	    }
	    if (acao.equalsIgnoreCase("Atualizar")) {
	        prato.setId(prato_id);
	    }

	    try {
	        if (acao.equalsIgnoreCase("Inserir")) {
	            pratoService.save(prato);
	            if (ingredientesIds != null) {
	                pratoIngredienteService.deleteByPrato(prato);
	                for (Long ingId : ingredientesIds) {
	                    Ingrediente ing = ingService.findById(ingId).orElse(null);
	                    if (ing != null) {
	                        Prato_Ingrediente pi = new Prato_Ingrediente();
	                        Prato_IngredienteId piId = new Prato_IngredienteId();
	                        piId.setPrato(prato);
	                        piId.setIngrediente(ing);
	                        pi.setPratoIngredienteId(piId);
	                        pi.setQtd(1); // ajustar qtd
	                        pratoIngredienteService.save(pi);
	                    }
	                }
	            }
	            prato = null;
	        }
	        if (acao.equalsIgnoreCase("Atualizar")) {
	            pratoService.update(prato);
	            // Atualizar ingredientes associados
	            if (ingredientesIds != null) {
	                pratoIngredienteService.deleteByPrato(prato);
	                for (Long ingId : ingredientesIds) {
	                    Ingrediente ing = ingService.findById(ingId).orElse(null);
	                    if (ing != null) {
	                        Prato_Ingrediente pi = new Prato_Ingrediente();
	                        Prato_IngredienteId piId = new Prato_IngredienteId();
	                        piId.setPrato(prato);
	                        piId.setIngrediente(ing);
	                        pi.setPratoIngredienteId(piId);
	                        pi.setQtd(1);
	                        pratoIngredienteService.save(pi);
	                    }
	                }
	            }
	            prato = null;
	        }
	        if (acao.equalsIgnoreCase("Excluir")) {
	            if (prato_id != null && !prato_id.isEmpty()) {
	                prato.setId(prato_id);
	                // Primeiro exclui as associações
	                pedidoPratoService.deleteByPrato(prato);
	                pratoIngredienteService.deleteByPrato(prato);
	                // Depois exclui o prato
	                pratoService.excluir(prato);
	                prato = null;
	            }
	        }
	        if (acao.equalsIgnoreCase("Buscar")) {
	            if (prato_id != null && !prato_id.isEmpty()) {
	                Optional<Prato> pratoOpt = pratoService.findById(prato_id); // corrigido para prato_id
	                if (pratoOpt.isPresent()) {
	                    prato = pratoOpt.get();
	                }
	            }
	        }
	    } catch (JDBCException e) {
	        erro = e.getMessage();
	    } finally {
	        List<Prato> pratos = pratoService.findAll();
	        model.addAttribute("pratos", pratos);
	        model.addAttribute("prato", prato);
	        model.addAttribute("erro", erro);
	    }
	    return "redirect:/admin";
	}

}