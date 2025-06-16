package fateczl.TrabalhoLabBd3.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fateczl.TrabalhoLabBd3.model.Pedido_Prato;
import fateczl.TrabalhoLabBd3.model.Prato;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface Pedido_PratoRepository extends JpaRepository<Pedido_Prato, Long> {

    @Modifying
    @Transactional
    void deleteByPrato(Prato prato);

}

