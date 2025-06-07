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

import fateczl.TrabalhoLabBd3.model.Cliente;
import fateczl.TrabalhoLabBd3.service.ClienteService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/clientes")
	public String clientes(Model model, @RequestParam Map<String, String> params) {
		String acao = params.get("acao");
		String clienteId = params.get("cliente_id");
		List<Cliente> clientes = clienteService.findAll();
		Cliente cliente = new Cliente();
		
		if (clienteId != null && !clienteId.isEmpty()) {
			Optional<Cliente> clienteOpt = clienteService.findById(clienteId);
			if (clienteOpt.isPresent()) {
				if (acao.equals("excluir")) {
					clienteService.excluir(clienteOpt.get());
					cliente = null;
				} else if (acao.equals("editar")) {
					cliente = clienteOpt.get();
				}
			}
		}
		model.addAttribute("clientes", clientes);
		model.addAttribute("cliente", cliente);
		return "clientes";
	}
	@PostMapping("/novoCliente")
	public String novoCliente(@RequestParam Map<String, String> params, ModelMap model, HttpServletResponse response) {
		Cliente cliente = new Cliente();
		cliente.setCpf(params.get("cpf"));
		cliente.setNome(params.get("nome"));
		cliente.setTelefone(params.get("telefone"));
		cliente.setEnd_log(params.get("end_log"));
		cliente.setEnd_num(Integer.parseInt(params.get("end_num")));
		cliente.setEnd_cep(params.get("end_cep"));
		cliente.setEnd_ponto_ref( params.get("end_ponto_ref"));
		clienteService.save(cliente);
		
		List<Cliente> clientes = clienteService.findAll();
		model.addAttribute("clientes", clientes);
		return "clientes";
	}
}











