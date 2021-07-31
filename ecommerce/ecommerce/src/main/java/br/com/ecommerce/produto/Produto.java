package br.com.ecommerce.produto;

import br.com.ecommerce.categoria.Categoria;
import br.com.ecommerce.produto.caracteristica.Caracteristicas;
import br.com.ecommerce.produto.caracteristica.CaracteristicasRequest;
import br.com.ecommerce.produto.imagem.Imagem;
import br.com.ecommerce.produto.opiniao.Opiniao;
import br.com.ecommerce.produto.opiniao.OpiniaoRequest;
import br.com.ecommerce.produto.pergunta.Pergunta;
import br.com.ecommerce.produto.pergunta.PerguntaRequest;
import br.com.ecommerce.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal valor;
    private Integer quantidade;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;
    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Caracteristicas> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<Imagem> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Opiniao> opinioes = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Pergunta> perguntas = new ArrayList<>();

    @ManyToOne
    private Usuario usuario;

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao,
                   Categoria categoria, Usuario usuario, List<CaracteristicasRequest> caracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas
                .stream()
                .map(c -> c.toModel(this))
                .collect(Collectors.toSet()));

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisar ter pelo menos" +
                " três características");
    }

    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getLoginUsuario() {
        return usuario.getEmail();
    }

    public void  addImagens(Set<String> links) {
        Set<Imagem> imagens = links.stream().map(link -> new Imagem(link, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public Produto(String descricao, String nome, BigDecimal valor){
        this.descricao = descricao;
        this.nome = nome;
        this.valor = valor;
    }


    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void addOpiniao(OpiniaoRequest opiniaoRequest, Usuario usuario){

        Opiniao opiniao = new Opiniao(opiniaoRequest.getNota(), opiniaoRequest.getTitulo(), opiniaoRequest.getDescricao(),
                usuario, this);

        this.opinioes.add(opiniao);
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    public Set<Caracteristicas> getCaracteristicas() {
        return caracteristicas;
    }

    public void addPergunta(PerguntaRequest novaPergunta, Usuario usuario) {
        Pergunta pergunta = new Pergunta(novaPergunta.getTitulo(), usuario, this);

        this.perguntas.add(pergunta);
    }
    public void abaterEstoque(int quantidade){
        this.quantidade = this.quantidade - quantidade;

    }


    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", instante=" + instante +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                ", opinioes=" + opinioes +
                ", perguntas=" + perguntas +
                ", usuario=" + usuario +
                '}';
    }



}