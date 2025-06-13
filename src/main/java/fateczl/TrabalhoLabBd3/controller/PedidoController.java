package fateczl.TrabalhoLabBd3.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fateczl.TrabalhoLabBd3.model.Pedido;
import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.service.PedidoService;
import fateczl.TrabalhoLabBd3.service.PratoService;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("pedido")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private PratoService pratoService;
	
	@ModelAttribute("pedido")
	public Pedido criarPedido() {
		return new Pedido();
	}
	
	@GetMapping("novoPedido")
	public String pedido(@ModelAttribute("pedido") Pedido pedido, Model model,
			@RequestParam Map<String, String> params) {
		List<Prato> pratos = pratoService.findAll();
		
		String acao = params.get("acao");
		String prato_id = params.get("prato_id");
		
		if (acao.equalsIgnoreCase("Adicionar")) {
			Optional<Prato> pratoOpt = pratoService.findById(prato_id);
			if (pratoOpt.isPresent()) {
				pedido.adicionarPrato(pratoOpt.get());
			}
		}
		if (acao.equalsIgnoreCase("Remover")) {
			Optional<Prato> pratoOpt = pratoService.findById(prato_id);
			if (pratoOpt.isPresent()) {
				pedido.removerPrato(pratoOpt.get());
			}
		}
		if (acao.equalsIgnoreCase("Limpar")) {
			pedido.limpar();
		}
		
		model.addAttribute("pratos", pratos);
		return "novoPedido";
	}
	
	@PostMapping("crudPedido")
	public String crudPedido(@ModelAttribute("pedido") Pedido pedido, @RequestParam Map<String, String> params) {
		String acao = params.get("acao");
		BigDecimal valorTotal = BigDecimal.valueOf(pratoService.calculaValorTotal(pedido.getPratos()));
		pedido.setValor_total(valorTotal);
		
		/*precisa implementar uma funcao de: 
		 * 	se o CookieValue ("cliente_id") existir, ele procura o clienteService.findById(cliente_id)
		 * 	e adiciona o Cliente retornado no pedido.addCliente(cliente).
		 * 
		 * 	se nao houver CookieValue ("cliente_id"), teria que mandar ele pra uma pagina
		 * 	de cadastro pra ele se cadastrar, pra enfim retornar o CookieValue dele
		 * 	e adicionar o novo Cliente no Pedido.*/
		
		if (acao.equalsIgnoreCase("Inserir")) {
			pedidoService.save(pedido);
		}
		
		return "novoPedido";
	}
	
}
