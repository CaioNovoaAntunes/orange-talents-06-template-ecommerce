package br.com.ecommerce.finalizacomprapart2;

import br.com.ecommerce.produto.pedido.Pedido;
import br.com.ecommerce.produto.pedido.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class finalizaCompraController {

    private final PedidoRepository pedidoRepository;
    private final Set<EventoPedido> eventoPedidos = new HashSet<>();
    private final EmailParaCliente email;

    public finalizaCompraController(PedidoRepository pedidoRepository, EmailParaCliente email,
                                    Set<EventoPedido> eventoPedidos) {

        this.pedidoRepository = pedidoRepository;
        this.email = email;
        this.eventoPedidos.addAll(eventoPedidos);
    }



    @PostMapping("retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> retornoPagSeguro(@PathVariable Long id,
                                              @Valid TransacaoRequestPagseguro request) {

        return processaPagamento(id, request);
    }


    @PostMapping("retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> retornoPaypal(@PathVariable Long id, TransacaoRequestPaypal request) {

        return processaPagamento(id, request);
    }


    private ResponseEntity<?> processaPagamento(Long id, TransacaoRequest request)  {

        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if(pedido.isEmpty()){
            ResponseEntity.notFound().build();
        }

        pedido.get().addTransacao(request);

        if(pedido.get().concluidaComSucesso()){
            eventoPedidos.forEach(eventoPedido -> eventoPedido.processa(pedido.get()));
        } else {
            email.compraNaoConfirmada(pedido.get());
        }

        return ResponseEntity.ok().build();
    }
}
