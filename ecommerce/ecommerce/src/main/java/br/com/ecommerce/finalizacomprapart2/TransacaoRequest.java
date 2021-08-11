package br.com.ecommerce.finalizacomprapart2;

import br.com.ecommerce.produto.pedido.Pedido;

public interface TransacaoRequest {

    /**
     *
     * @param pedido
     * @return retorna uma nova transação
     * dependendo do tipo de pagamento
     * (paypal, pagseguro, entre outros, se houver)
     */
    Transacao toModel(Pedido pedido);
}
