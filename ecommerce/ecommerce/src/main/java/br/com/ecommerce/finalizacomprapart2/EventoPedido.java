package br.com.ecommerce.finalizacomprapart2;

import br.com.ecommerce.produto.pedido.Pedido;

public interface EventoPedido {
    void processa(Pedido pedido);
}
