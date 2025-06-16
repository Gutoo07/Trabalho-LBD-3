package fateczl.TrabalhoLabBd3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fateczl.TrabalhoLabBd3.model.Cliente;
import fateczl.TrabalhoLabBd3.persistence.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository repCliente;
	
	public List<Cliente> findAll() {
		return repCliente.findAll();
	}
	public Optional<Cliente> findById(String cpf) {
		return repCliente.findById(cpf);
	}
	public void excluir(Cliente cliente) {
		repCliente.delete(cliente);
	}
	public void save(Cliente cliente) {
		repCliente.save(cliente);
	}
	
	public Cliente findByUserSession(String userSession) {
	    if (userSession == null || userSession.isEmpty()) {
	        return null;
	    }
	    return repCliente.findById(userSession).orElse(null);
	}

}
