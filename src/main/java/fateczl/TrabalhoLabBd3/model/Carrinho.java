package fateczl.TrabalhoLabBd3.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Prato> items = new ArrayList<>();

    public void addItem(Prato prato) {
        items.add(prato);
    }

    public void removeItem(Prato prato) {
        items.remove(prato);
    }

    public List<Prato> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}
