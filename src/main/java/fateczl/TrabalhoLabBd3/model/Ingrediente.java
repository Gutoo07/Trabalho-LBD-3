package fateczl.TrabalhoLabBd3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ingrediente")
@Getter
@Setter
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	@Column(name = "apresentacao", length = 50, nullable = false)
	private String apresentacao;
}
