package br.com.ecommerce.categoria;

import br.com.ecommerce.validador.CheckIfExist;
import br.com.ecommerce.validador.UniqueValue;
import org.hibernate.annotations.Check;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaDTO {
    @UniqueValue(campo = "nome", Classe = Categoria.class)
    private String nome;

    private Long idCategoriaMae;



    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }




    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "nome='" + nome + '\'' +
                ", idCategoriaMae=" + idCategoriaMae +
                '}';
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);

        if(this.idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }

        return categoria;
    }

}