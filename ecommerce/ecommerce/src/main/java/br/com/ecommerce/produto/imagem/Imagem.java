package br.com.ecommerce.produto.imagem;

import br.com.ecommerce.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @URL
    private String link;
    @ManyToOne()
    @NotNull
    @Valid
    private Produto produto;

    @Deprecated
    public Imagem() {

    }
    public Imagem(String link, Produto produto) {
        this.id = id;
        this.link = link;
        this.produto = produto;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagem imagem = (Imagem) o;
        return link.equals(imagem.link) && produto.equals(imagem.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, produto);
    }
}