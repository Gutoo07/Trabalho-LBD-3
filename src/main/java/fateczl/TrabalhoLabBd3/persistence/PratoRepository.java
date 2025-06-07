package fateczl.TrabalhoLabBd3.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fateczl.TrabalhoLabBd3.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, String>{

}
