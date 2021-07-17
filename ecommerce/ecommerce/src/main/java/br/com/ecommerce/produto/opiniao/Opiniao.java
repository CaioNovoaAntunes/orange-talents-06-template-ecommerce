package br.com.ecommerce.produto.opiniao;

import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.usuario.Usuario;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int nota;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, columnDefinition = "TEXT", length = 500)
    private String descricao;
    @ManyToOne
    @Valid
    private Usuario usuario;
    @ManyToOne
    @Valid
    private Produto produto;

    public Opiniao(int nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public Opiniao() {
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
