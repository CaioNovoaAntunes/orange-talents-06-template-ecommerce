package br.com.ecommerce.finalizacomprapart2;

import br.com.ecommerce.produto.pedido.Pedido;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoRequestPagseguro implements TransacaoRequest {

    @NotNull
    private String idTransacao;
    @NotNull
    private StatusPagseguro status;

    public TransacaoRequestPagseguro(String idTransacao, StatusPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusPagseguro getStatusPagseguro() {
        return status;
    }

    @Override
    public Transacao toModel(Pedido pedido) {
        return new Transacao(this.idTransacao, this.status.normaliza(), pedido);
    }

}
