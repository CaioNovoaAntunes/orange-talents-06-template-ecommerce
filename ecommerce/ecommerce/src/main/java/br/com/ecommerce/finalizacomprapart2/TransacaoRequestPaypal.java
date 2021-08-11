package br.com.ecommerce.finalizacomprapart2;

import br.com.ecommerce.produto.pedido.Pedido;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransacaoRequestPaypal implements TransacaoRequest{

    @NotNull
    private String idTransacao;
    @NotNull
    @Min(0) @Max(1)
    private Integer status;

    public TransacaoRequestPaypal(String idTransacao, Integer status, Long idPedido) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public Transacao toModel(Pedido pedido) {
        return new Transacao(this.idTransacao, StatusPaypal.normaliza(this.status), pedido);
    }

}
