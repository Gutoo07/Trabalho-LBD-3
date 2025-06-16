package fateczl.TrabalhoLabBd3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {
	@Id
	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "telefone", length = 11, nullable = false)
	private String telefone;
	@Column(name = "end_log", length = 200, nullable = false)
	private String end_log;
	@Column(name = "end_num", nullable = false)
	private int end_num;
	@Column(name = "end_cep", length = 8, nullable = false)
	private String end_cep;
	@Column(name = "end_ponto_ref", length = 100, nullable = true)
	private String end_ponto_ref;
	@Column(name = "senha", length = 20, nullable = false)
	private String senha;
	
}
