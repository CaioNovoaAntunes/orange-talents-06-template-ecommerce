package br.com.ecommerce.produto;

import br.com.ecommerce.categoria.CategoriaRepository;
import br.com.ecommerce.usuario.Usuario;
import br.com.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody @Valid NovoProdutoRequest request,
                                           @AuthenticationPrincipal Usuario usuario) {

        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getUsername());
        Produto produto = request.toModel(categoriaRepository, user.get());
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }
}




