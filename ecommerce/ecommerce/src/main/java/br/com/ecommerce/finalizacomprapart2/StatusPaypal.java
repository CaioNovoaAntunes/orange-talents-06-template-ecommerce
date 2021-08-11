package br.com.ecommerce.finalizacomprapart2;

public enum StatusPaypal {

    SUCESSO(1),
    ERRO(0);

    private int descricao;

    StatusPaypal(int descricao) {
        this.descricao = descricao;
    }

    public static StatusTransacao normaliza(Integer status) {
        if(status == 1){
            return StatusTransacao.SUCESSO;
        }

        if(status == 0){
            return StatusTransacao.FALHA;
        }

        return StatusTransacao.FALHA;

    }
}
