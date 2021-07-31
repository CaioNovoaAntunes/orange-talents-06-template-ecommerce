package br.com.ecommerce.produto.pedido;

import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.ProdutoRepository;

import br.com.ecommerce.produto.pergunta.EmailParaDonoProduto;
import br.com.ecommerce.usuario.Usuario;
import br.com.ecommerce.usuario.UsuarioRepository;
import br.com.ecommerce.validador.handler.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PedidoController {

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final EmailParaDonoProduto email;

    public PedidoController(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository,
                            PedidoRepository pedidoRepository, EmailParaDonoProduto email) {

        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.email = email;
    }

    @PostMapping("/produtos/pedido")
    @Transactional
    public ResponseEntity<?> compra(@RequestBody @Valid PedidoRequest request,
                                    @AuthenticationPrincipal Usuario user) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getUsername());

        Pedido pedido = request.toModel(produtoRepository, usuario.get());

        try {
            MetodoPagamento.eValido(request.getMetodoPagamento());
        }catch (IllegalArgumentException e){

            return ResponseEntity.badRequest().body(
                    new Errors("metodoPagamento", "Forma de pagamento inexistente " +
                            request.getMetodoPagamento()));
        }

        pedidoRepository.save(pedido);
        String pagamento = request.getMetodoPagamento();
        String url = MetodoPagamento.valueOf(pagamento).realizarPagamento(pedido);
        email.avisoDeCompra(pedido);
        return ResponseEntity.status(303).body(url);
    }

}