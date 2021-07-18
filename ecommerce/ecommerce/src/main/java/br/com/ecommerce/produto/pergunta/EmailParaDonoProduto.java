package br.com.ecommerce.produto.pergunta;

import org.springframework.stereotype.Component;

@Component
public class EmailParaDonoProduto {

    public void enviarPergunta(PerguntaRequest texto) {
        System.out.println("Uma nova pergunta foi feita para o seu produto: \n" +
                texto.toString() + "\nClique aqui para responder");
    }

   /*
   será utilizado posteriormente para enviar o aviso de compra

   public void avisoDeCompra(Pedido pedido) {
        System.out.println("Parabéns!!! \n" +
                "Seu produto acaba de ser comprado. \n" +
                "Comprador: " + pedido.getUsuario().getLogin() + "\n");
    }*/
}