package fateczl.TrabalhoLabBd3.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prato_ingrediente")
@Getter
@Setter
public class Prato_Ingrediente implements java.io.Serializable {
	@EmbeddedId
	private Prato_IngredienteId pratoIngredienteId;
	@Column(name = "qtd", nullable = false)
	private int qtd;
	
	/*
	@ManyToOne
	@JoinColumn(name = "prato_id")
	private Prato prato;
	
	@ManyToOne
	@JoinColumn(name = "ingrediente_id")
	private Ingrediente ingrediente; */
}
