package fateczl.TrabalhoLabBd3.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fateczl.TrabalhoLabBd3.model.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
	public Optional<Ingrediente> findByNome(String nome);
}
