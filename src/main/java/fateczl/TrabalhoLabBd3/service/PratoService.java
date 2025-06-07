package fateczl.TrabalhoLabBd3.service;

import java.util.List;

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
		for (int i = 0; i < 10; i++) {
			if (pratos != null) {
				do {
					idUnico = true;
					int random = (int) (Math.random() * 10000);
					novoId = "P"+random;
					System.out.println("Novo prato #"+novoId);
					for (Prato p : pratos) {
						if (p.getId().equals(novoId)) {
							System.err.println("=====================================ID EXISTENTE=========================================");
							
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
	}
}
