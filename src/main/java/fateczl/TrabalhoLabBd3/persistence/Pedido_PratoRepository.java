package fateczl.TrabalhoLabBd3.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fateczl.TrabalhoLabBd3.model.Pedido_Prato;

@Repository
public interface Pedido_PratoRepository extends JpaRepository<Pedido_Prato, Long>{

}
