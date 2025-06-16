package fateczl.TrabalhoLabBd3.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
//import org.springframework.data.annotation.Transient;
//Esse import do transient tava com erro aqui no meu Gu então encontrei num forum pra trocar por esse aqui embaixo
import jakarta.persistence.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "dia_hora", nullable = false)
	private LocalDateTime dia_hora;
	@Column(name = "valor_total", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor_total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@Transient
	private List<Prato> pratos;
	
	public void adicionarPrato(Prato prato) {
		if (this.pratos == null) {
			pratos = new ArrayList<>();
		}
		pratos.add(prato);
	}
	public void removerPrato(Prato prato) {
		pratos.remove(prato);
	}
	public void limpar() {
		pratos.clear();
	}
}
