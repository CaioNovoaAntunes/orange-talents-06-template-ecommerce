package br.com.ecommerce.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {
    @NotBlank
    private String titulo;

    @JsonCreator
    public PerguntaRequest(String titulo){
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
    @Override
    public String toString(){
        return "Mensagem " + this.titulo;
    }
}
