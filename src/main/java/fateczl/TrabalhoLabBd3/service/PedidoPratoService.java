package fateczl.TrabalhoLabBd3.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fateczl.TrabalhoLabBd3.model.Pedido;
import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.persistence.PedidoRepository;
import fateczl.TrabalhoLabBd3.persistence.Pedido_PratoRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PedidoPratoService {

    @Autowired
    private Pedido_PratoRepository pedidoPratoRepository;

    @Transactional
    public void deleteByPrato(Prato prato) {
        pedidoPratoRepository.deleteByPrato(prato);
    }
}
