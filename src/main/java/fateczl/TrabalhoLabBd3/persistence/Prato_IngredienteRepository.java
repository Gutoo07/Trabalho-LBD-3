package fateczl.TrabalhoLabBd3.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.model.Prato_Ingrediente;
import fateczl.TrabalhoLabBd3.model.Prato_IngredienteId;

@Repository
public interface Prato_IngredienteRepository extends JpaRepository<Prato_Ingrediente, Prato_IngredienteId>{
	List<Prato_Ingrediente> findByPratoIngredienteId_Prato(Prato prato);
}
