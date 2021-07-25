package br.com.ecommerce.produto.detalheproduto;

import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DetalheProdutoController {

    private ProdutoRepository produtoRepository;

    public DetalheProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<DetalheProdutoView> detalharProduto(@PathVariable(name = "id") Long idProduto){

        Optional<Produto> produtoEncontrado = produtoRepository.findById(idProduto);

        if(!produtoEncontrado.isPresent()){
            return ResponseEntity.notFound().build();
        }

        DetalheProdutoView response = new DetalheProdutoView(produtoEncontrado.get());

        return ResponseEntity.ok(response);
    }
}