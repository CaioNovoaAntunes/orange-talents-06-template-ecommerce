package br.com.ecommerce.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;


    public Categoria(String nome) {
        this.nome = nome;

    }

    @Deprecated
    public Categoria() {
    }

    public String getNome() {
        return this.nome;
    }

    public Long getId() {
        return id;
    }




    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}