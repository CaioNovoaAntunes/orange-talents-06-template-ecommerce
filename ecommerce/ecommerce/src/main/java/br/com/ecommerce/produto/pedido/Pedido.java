package br.com.ecommerce.produto.pedido;

import br.com.ecommerce.finalizacomprapart2.TransacaoRequest;
import br.com.ecommerce.finalizacomprapart2.Transacao;
import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.status.Status;
import br.com.ecommerce.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @ManyToMany
    @Valid
    private List<Produto> produtos = new ArrayList<>();
    @Column(nullable = false)
    private Integer quantidade;
    @ManyToOne
    @Valid
    private Usuario usuario;
    private BigDecimal valorUnitario;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Set<Transacao> transacoes = new HashSet<>();

    public Pedido(String metodoPagamento, Produto produto, Integer quantidade, Usuario usuario,
                  Status status) {
        this.metodoPagamento = MetodoPagamento.valueOf(metodoPagamento.toUpperCase());
        this.produtos.add(produto);
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.valorUnitario = produto.getValor();
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

    public void addTransacao(TransacaoRequest request){
        Transacao transacao = request.toModel(this);
        Assert.isTrue(!this.transacoes.contains(transacao), "Transação já existente");

        Set<Transacao> transacoesConcluidas = transacaoConcluida();

        Assert.isTrue(transacoesConcluidas.isEmpty(), "Processo de pagamento ja concluído anteriormente");

        this.transacoes.add(transacao);
    }

    public boolean concluidaComSucesso() {
        if (transacaoConcluida().isEmpty()){
            return false;
        }
        return true;
    }

    private Set<Transacao> transacaoConcluida() {
        return this.transacoes.stream()
                .filter(Transacao::estaConcluida)
                .collect(Collectors.toSet());
    }
}

