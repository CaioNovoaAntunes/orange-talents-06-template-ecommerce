package br.com.ecommerce.notafiscal;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long idPedido;
    @NotNull
    private Long idUsuario;

    public NotaFiscalRequest(Long idPedido, Long idUsuario) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "idPedido=" + idPedido +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
