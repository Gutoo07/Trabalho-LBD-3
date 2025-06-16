package fateczl.TrabalhoLabBd3.persistence;

import java.util.List;

import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.model.Prato_Ingrediente;

public class PratoComIngredientesDTO {
    private Prato prato;
    private List<Prato_Ingrediente> ingredientes;

    public PratoComIngredientesDTO(Prato prato, List<Prato_Ingrediente> ingredientes) {
        this.prato = prato;
        this.ingredientes = ingredientes;
    }

    public Prato getPrato() {
        return prato;
    }

    public List<Prato_Ingrediente> getIngredientes() {
        return ingredientes;
    }
}
