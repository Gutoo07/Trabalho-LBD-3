package fateczl.TrabalhoLabBd3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fateczl.TrabalhoLabBd3.model.Tipo;
import fateczl.TrabalhoLabBd3.persistence.TipoRepository;

@Service
public class TipoService {
	@Autowired
	TipoRepository repTipo;
	
	public Optional<Tipo> findById(Long id) {
		return repTipo.findById(id);
	}
	public void save(Tipo tipo) {
		repTipo.save(tipo);
	}
}
