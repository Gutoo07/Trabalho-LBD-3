package fateczl.TrabalhoLabBd3.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prato")
@Getter
@Setter
public class Prato {
	@Id
	@Column(name = "id", length = 5, nullable = false)
	private String id;
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	@Column(name = "tamanho_porcao", nullable = false)
	private int tamanho_porcao;
	@Column(name = "valor", precision = 7, scale = 2, nullable = true)
	private BigDecimal valor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_id", nullable = false)
	private Tipo tipo;
}
