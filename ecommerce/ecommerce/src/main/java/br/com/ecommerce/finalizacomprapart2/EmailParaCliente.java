package br.com.ecommerce.finalizacomprapart2;

import br.com.ecommerce.produto.pedido.Pedido;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmailParaCliente implements EventoPedido{

    void compraConfirmada(Pedido pedido){
        System.out.println("Sua compra foi faturada: \n" +
                "Produto: " + pedido.getProdutos().get(0).getNome() + "\n" +
                "Valor: " + pedido.getProdutos().get(0).getValor());
    }

    void compraNaoConfirmada(Pedido pedido){
        System.out.println("Infelizmente tivemos um problema ao processar seu pedido: \n" +
                "Produto: " + pedido.getProdutos().get(0).getNome() + "\n" +
                "Valor: " + pedido.getProdutos().get(0).getValor() + "\n" +
                "Tente pagar novamente: http://suacompra.com/" + Objects.hashCode(pedido.getProdutos().get(0)));
    }

    @Override
    public void processa(Pedido pedido) {
        compraConfirmada(pedido);
    }
}
