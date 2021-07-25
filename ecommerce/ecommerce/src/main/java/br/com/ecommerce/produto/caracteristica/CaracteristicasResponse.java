package br.com.ecommerce.produto.caracteristica;

public class CaracteristicasResponse {

    private String nome;
    private String descricao;

    public CaracteristicasResponse(Caracteristicas caracteristicas) {
        this.nome = caracteristicas.getNome();
        this.descricao = caracteristicas.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}