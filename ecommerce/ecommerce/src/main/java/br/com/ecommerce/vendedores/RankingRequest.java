package br.com.ecommerce.vendedores;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long idPedido;
    @NotNull
    private Long idVendedor;

    public RankingRequest(Long idPedido, Long idVendedor) {
        this.idPedido = idPedido;
        this.idVendedor = idVendedor;
    }


    @Override
    public String toString() {
        return "RankingRequest{" +
                "idPedido=" + idPedido +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
