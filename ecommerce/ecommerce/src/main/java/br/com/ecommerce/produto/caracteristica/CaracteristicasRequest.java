package br.com.ecommerce.produto.caracteristica;

import br.com.ecommerce.produto.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicasRequest {

        @NotBlank
        private String nome;
        @NotBlank
        private String descricao;

        public CaracteristicasRequest(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }


        public Caracteristicas toModel(Produto produto) {

            return new Caracteristicas(this.nome, this.descricao, produto);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CaracteristicasRequest that = (CaracteristicasRequest) o;
            return nome.equals(that.nome);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nome);
        }
    }
