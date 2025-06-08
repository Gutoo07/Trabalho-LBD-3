package fateczl.TrabalhoLabBd3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.persistence.PratoRepository;

@Service
public class PratoService {
	@Autowired
	PratoRepository repPrato;
	
	public void save(Prato prato) {
		List<Prato> pratos = repPrato.findAll();

		String novoId = "";
		boolean idUnico;
		if (pratos != null) {
			do {
				idUnico = true;
				int random = (int) (Math.random() * 10000);
				novoId = "P"+random;
				for (Prato p : pratos) {
					if (p.getId().equals(novoId)) {
						idUnico = false;
						break;
					}
				}
			} while (!idUnico);
		} else {
			int random = (int) Math.random();
			novoId = "P"+random;
		}		
		prato.setId(novoId);
		repPrato.save(prato);
	}
	public List<Prato> findAll() {
		return repPrato.findAll();
	}
	public Optional<Prato> findById(String id) {
		return repPrato.findById(id);
	}
	public void excluir(Prato prato) {
		repPrato.delete(prato);
	}
	public void update(Prato prato) {
		repPrato.save(prato);
	}
}
