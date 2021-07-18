package br.com.ecommerce.produto.pergunta;

import br.com.ecommerce.categoria.CategoriaRepository;
import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.ProdutoRepository;
import br.com.ecommerce.usuario.Usuario;
import br.com.ecommerce.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final EmailParaDonoProduto email;




    public PerguntaController(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository,
                              EmailParaDonoProduto email) {

        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.email = email;
    }

    @PostMapping("/produtos/{id}/pergunta")
    @Transactional
    public ResponseEntity<?> criarPergunta(@PathVariable(name = "id") Long idProduto,
                                           @RequestBody @Valid PerguntaRequest request,
                                           @AuthenticationPrincipal Usuario user){

        Optional<Produto> produto = produtoRepository.findById(idProduto);
        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getUsername());

        if(produto.isEmpty() || usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        email.enviarPergunta(request);
        produto.get().addPergunta(request, usuario.get());

        return ResponseEntity.ok().build();
    }

}
