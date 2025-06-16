package fateczl.TrabalhoLabBd3.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fateczl.TrabalhoLabBd3.model.Carrinho;
import fateczl.TrabalhoLabBd3.model.Cliente;
import fateczl.TrabalhoLabBd3.model.Pedido;
import fateczl.TrabalhoLabBd3.model.Pedido_Prato;
import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.model.Prato_Ingrediente;
import fateczl.TrabalhoLabBd3.persistence.PedidoRepository;
import fateczl.TrabalhoLabBd3.persistence.Pedido_PratoRepository;
import fateczl.TrabalhoLabBd3.persistence.PratoComIngredientesDTO;
import fateczl.TrabalhoLabBd3.persistence.PratoRepository;
import fateczl.TrabalhoLabBd3.persistence.Prato_IngredienteRepository;
import fateczl.TrabalhoLabBd3.service.ClienteService;
import fateczl.TrabalhoLabBd3.service.PedidoService;
import fateczl.TrabalhoLabBd3.service.PratoService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("carrinho")
public class ViewController {
	@Autowired
	ClienteService clienteService;
	@Autowired
	PratoRepository pratoRep;
	@Autowired
	private Prato_IngredienteRepository pratoIngredienteRep;
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private PratoService pratoService;
		
	@Autowired
	private PedidoRepository repPedido;
	
	@Autowired
	private Pedido_PratoRepository repPedidoPrato;
	
	
    @ModelAttribute("carrinho")
    public Carrinho inicializarCarrinho() {
        return new Carrinho();
    }
	
	@GetMapping("/")
	public String login(Model model, @RequestParam Map<String, String> params) throws ClassNotFoundException, SQLException {
		
		return "login";
	}
	
	@GetMapping("/cardapio")
	public String cardapio(Model model) {
	    List<Prato> pratos = pratoRep.findAll();
	    List<PratoComIngredientesDTO> pratosComIngredientes = new ArrayList<>();

	    for (Prato prato : pratos) {
	        List<Prato_Ingrediente> ingredientes = pratoIngredienteRep.findByPratoIngredienteId_Prato(prato);
	        pratosComIngredientes.add(new PratoComIngredientesDTO(prato, ingredientes));
	    }

	    model.addAttribute("pratosComIngredientes", pratosComIngredientes);
	    return "cardapio";
	}
	
	@GetMapping("/carrinho")
	public String verCarrinho(@ModelAttribute("carrinho") Carrinho carrinho, Model model) {
	    List<Prato> itens = carrinho.getItems();
	    model.addAttribute("itens", itens);
	    
	    BigDecimal total = itens.stream()
	        .map(Prato::getValor)
	        .filter(Objects::nonNull)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);

	    model.addAttribute("total", total);

	    return "carrinho";
	}

	
	@GetMapping("/admin")
	public String admin(Model model, @RequestParam Map<String, String> params) throws ClassNotFoundException, SQLException {
		
		return "admin";
	}
	
	@PostMapping("/finalizar-compra")
	public String finalizarCompra(@CookieValue(value = "userSession", defaultValue = "") String userSession,
	                             @ModelAttribute("carrinho") Carrinho carrinho) {
	    if (userSession == null || userSession.isEmpty()) {
	        return "redirect:/login";
	    }

	    Cliente cliente = clienteService.findByUserSession(userSession);
	    if (cliente == null) {
	        return "redirect:/login";
	    }


	    BigDecimal total = carrinho.getItems().stream()
	                       .map(Prato::getValor)
	                       .reduce(BigDecimal.ZERO, BigDecimal::add);


	    Pedido pedido = new Pedido();
	    pedido.setCliente(cliente);
	    pedido.setDia_hora(LocalDateTime.now());
	    pedido.setValor_total(total);


	    pedido = repPedido.save(pedido);


	    for (Prato prato : carrinho.getItems()) {
	        Pedido_Prato pedidoPrato = new Pedido_Prato();
	        pedidoPrato.setPedido(pedido);
	        pedidoPrato.setPrato(prato);
	        repPedidoPrato.save(pedidoPrato);
	    }


	    carrinho.clear();

	    return "redirect:/cardapio";
	}
	
	
	
	
	
}