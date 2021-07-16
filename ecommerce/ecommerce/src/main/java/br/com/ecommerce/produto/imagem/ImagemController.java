package br.com.ecommerce.produto.imagem;

import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.ProdutoRepository;
import br.com.ecommerce.usuario.Usuario;
import br.com.ecommerce.validador.handler.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
public class ImagemController {

    private final ProdutoRepository produtoRepository;
    private final Uploader uploadFake;


    public ImagemController(ProdutoRepository produtoRepository, UploadFake uploadFake) {
        this.produtoRepository = produtoRepository;
        this.uploadFake = uploadFake;
    }

    @PostMapping("/produtos/{id}/imagens")
    @Transactional
    public ResponseEntity<?> upload(@PathVariable Long id, @Valid NovaImagenRequest request,
                                    @AuthenticationPrincipal Usuario user){

        Optional<Produto> producoEncontrado = produtoRepository.findById(id);
        if(producoEncontrado.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errors("produto", "" +
                    "produto não encontado"));
        }

        if(!producoEncontrado.get().getLoginUsuario().equals(user.getUsername())){

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Set<String> links = uploadFake.enviar(request.getImagens());
        producoEncontrado.get().addImagens(links);

        return ResponseEntity.ok().build();

    }
}
