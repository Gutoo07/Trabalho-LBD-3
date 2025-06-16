package fateczl.TrabalhoLabBd3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fateczl.TrabalhoLabBd3.model.Carrinho;
import fateczl.TrabalhoLabBd3.model.Prato;
import fateczl.TrabalhoLabBd3.persistence.PratoRepository;
@Controller
@SessionAttributes("carrinho") // carrinho será mantido na sessão do usuário
public class CarrinhoController {

    @ModelAttribute("carrinho")
    public Carrinho inicializarCarrinho() {
        return new Carrinho();
    }

    @Autowired
    private PratoRepository pratoRep;

    @PostMapping("/add-to-cart")
    public String adicionarAoCarrinho(@RequestParam("pratoId") String pratoId, @ModelAttribute("carrinho") Carrinho carrinho) {
        Optional<Prato> pratoOpt = pratoRep.findById(pratoId);
        pratoOpt.ifPresent(carrinho::addItem); // adiciona o prato no carrinho
        return "redirect:/cardapio";
    }
    
    @PostMapping("/remover-item")
    public String removerItemDoCarrinho(@RequestParam("pratoId") String pratoId,
                                        @ModelAttribute("carrinho") Carrinho carrinho) {
 
        carrinho.getItems().removeIf(prato -> prato.getId().equals(pratoId));
        return "redirect:/carrinho"; 
    }



}


