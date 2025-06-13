package fateczl.TrabalhoLabBd3.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fateczl.TrabalhoLabBd3.model.Pedido;
import fateczl.TrabalhoLabBd3.persistence.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository rep;
	
	public void save(Pedido pedido) {
		LocalDateTime dia_hora = LocalDateTime.now();
		pedido.setDia_hora(dia_hora);		
	}
}
