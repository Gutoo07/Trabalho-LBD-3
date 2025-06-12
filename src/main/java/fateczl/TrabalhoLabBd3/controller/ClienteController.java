package fateczl.TrabalhoLabBd3.controller;

import java.util.ArrayList;
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
	@PostMapping("/crudCliente")
	public String crudCliente(@RequestParam Map<String, String> params, ModelMap model, HttpServletResponse response) {
		Cliente cliente = new Cliente();
		String cpf = params.get("cpf");
		String nome = params.get("nome");
		String telefone = params.get("telefone");
		String end_log = params.get("end_log");
		String end_num = params.get("end_num");
		String end_cep = params.get("end_cep");
		String end_ponto_ref = params.get("end_ponto_ref");
		
		String acao = params.get("acao");
		
		if (acao.equalsIgnoreCase("Inserir") || acao.equalsIgnoreCase("Atualizar")) {
			cliente.setNome(nome);
			cliente.setTelefone(telefone);
			cliente.setEnd_log(end_log);
			cliente.setEnd_num(Integer.parseInt(end_num));
			cliente.setEnd_cep(end_cep);
			cliente.setEnd_ponto_ref(end_ponto_ref);
		}
		if (acao.equalsIgnoreCase("Atualizar") || acao.equalsIgnoreCase("Excluir") || acao.equalsIgnoreCase("Inserir")) {
			cliente.setCpf(cpf);
		}
			
		String erro = "";
		try {
			if (acao.equalsIgnoreCase("Inserir")) {
				if (cliente.getCpf() != null) {										
					if (clienteService.findById(cliente.getCpf()).isPresent()) {
						erro = "Um cliente com esse CPF ja existe.";
					} else {
						clienteService.save(cliente);
						cliente = null;
					}						
				} else {
					erro = "CPF nao informado";
				}
			}
			if (acao.equalsIgnoreCase("Atualizar")) {
				if (cliente.getCpf() != null) {
					if (clienteService.findById(cliente.getCpf()).isPresent()) {
						clienteService.save(cliente);
						cliente = null;
					} else {
						erro = "Cliente inexistente";
					}
				} else {
					erro = "CPF nao informado";
				}
			}
			if (acao.equalsIgnoreCase("Excluir")) {
				if (cliente.getCpf() != null) {
					if (clienteService.findById(cliente.getCpf()).isPresent()) {
						clienteService.excluir(cliente);
						cliente = null;
					} else {
						erro = "Cliente inexistente";
					}
				}  else {
					erro = "CPF nao informado";
				}
			}
			if (acao.equalsIgnoreCase("Buscar")) {
				if (cpf != null && !cpf.isEmpty()) {
					Optional<Cliente> clienteOpt = clienteService.findById(cpf);
					if (clienteOpt.isPresent()) {
						cliente = clienteOpt.get();
					} else {
						erro = "Cliente inexistente";
					}
				} else {
					erro = "CPF nao informado";
				}
			}
			System.err.println(erro);
		} catch (JDBCException e) {
			erro = e.getMessage();
		} finally {
			List<Cliente> clientes = clienteService.findAll();
			model.addAttribute("cliente", cliente);
			model.addAttribute("clientes", clientes);
			model.addAttribute("erro", erro);
		}		
		return "clientes";
	}
}