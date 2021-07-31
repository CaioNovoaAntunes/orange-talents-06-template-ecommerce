package br.com.ecommerce.produto.pedido;

import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.status.Status;
import br.com.ecommerce.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @ManyToMany
    private List<Produto> produtos = new ArrayList<>();
    @Column(nullable = false)
    private Integer quantidade;
    @ManyToOne
    private Usuario usuario;
    private BigDecimal valorUnitario;
    @Enumerated(EnumType.STRING)
    private Status status;


    public Pedido(String metodoPagamento, Produto produto, Integer quantidade, Usuario usuario,
                  Status status) {
        this.metodoPagamento = MetodoPagamento.valueOf(metodoPagamento.toUpperCase());
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.valorUnitario = valorUnitario;
        this.status = status;

        produto.abaterEstoque(this.quantidade);
    }
    @Deprecated
    public Pedido() {
    }

    public Long getId() {
        return id;
    }


    public List<Produto> getProdutos() {
        return produtos;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
