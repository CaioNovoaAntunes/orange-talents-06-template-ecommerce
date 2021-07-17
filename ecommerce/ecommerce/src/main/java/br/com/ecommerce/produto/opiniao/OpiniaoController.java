package br.com.ecommerce.produto.opiniao;

import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.ProdutoRepository;
import br.com.ecommerce.usuario.Usuario;
import br.com.ecommerce.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class OpiniaoController {
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;


    public OpiniaoController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @PostMapping("/produtos/{id}/opiniao")
    @Transactional
    public ResponseEntity<String> adicionaOpiniao(@PathVariable(name = "id") Long idProduto,
                                                  @RequestBody @Valid OpiniaoRequest request,
                                                  @AuthenticationPrincipal Usuario user) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getUsername());
        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if (produto.isEmpty() || usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        produto.get().addOpiniao(request, usuario.get());
        return ResponseEntity.ok().build();
    }


}
