package br.com.ecommerce.produto.pergunta;

import br.com.ecommerce.finalizacomprapart2.EventoPedido;
import br.com.ecommerce.produto.pedido.Pedido;
import org.springframework.stereotype.Component;

@Component
public class EmailParaDonoProduto {

    public void enviarPergunta(PerguntaRequest texto) {
        System.out.println("Uma nova pergunta foi feita para o seu produto: \n" +
                texto.toString() + "\nClique aqui para responder");
    }




   public void avisoDeCompra(Pedido pedido) {
        System.out.println("Parabéns!!! \n" +
                "Seu produto acaba de ser comprado. \n" +
                "Comprador: " + pedido.getUsuario().getEmail() + "\n");
    }

}