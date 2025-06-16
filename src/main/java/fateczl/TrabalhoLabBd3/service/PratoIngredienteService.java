package fateczl.TrabalhoLabBd3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fateczl.TrabalhoLabBd3.model.Cliente;
import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.model.Prato_Ingrediente;
import fateczl.TrabalhoLabBd3.persistence.ClienteRepository;
import fateczl.TrabalhoLabBd3.persistence.Prato_IngredienteRepository;

@Service
public class PratoIngredienteService {
    @Autowired
    private Prato_IngredienteRepository repo;

    public void deleteByPrato(Prato prato) {
        List<Prato_Ingrediente> lista = repo.findByPratoIngredienteId_Prato(prato);
        repo.deleteAll(lista);
    }

    public void save(Prato_Ingrediente pi) {
        repo.save(pi);
    }
}

