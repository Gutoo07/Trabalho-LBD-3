package fateczl.TrabalhoLabBd3.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class Prato_IngredienteId implements Serializable {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prato_id")
	private Prato prato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingrediente_id")
	private  Ingrediente ingrediente;
}
