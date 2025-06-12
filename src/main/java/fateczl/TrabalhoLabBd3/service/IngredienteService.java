package fateczl.TrabalhoLabBd3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fateczl.TrabalhoLabBd3.model.Ingrediente;
import fateczl.TrabalhoLabBd3.persistence.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	IngredienteRepository rep;
	
	public Optional<Ingrediente> findById(Long id) {
		return rep.findById(id);
	}
	public Optional<Ingrediente> findByNome(String nome) {
		return rep.findByNome(nome);
	}
	public void save(Ingrediente ingrediente) {
		rep.save(ingrediente);
	}
	public  void excluir(Ingrediente ingrediente) {
		rep.delete(ingrediente);
	}
	public List<Ingrediente> findAll() {
		return rep.findAll();
	}
}
