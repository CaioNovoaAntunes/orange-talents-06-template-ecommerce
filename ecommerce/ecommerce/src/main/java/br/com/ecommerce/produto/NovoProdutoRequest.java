package br.com.ecommerce.produto;

import br.com.ecommerce.categoria.Categoria;
import br.com.ecommerce.categoria.CategoriaRepository;
import br.com.ecommerce.produto.caracteristica.CaracteristicasRequest;
import br.com.ecommerce.usuario.Usuario;
import br.com.ecommerce.validador.CheckIfExist;
import br.com.ecommerce.validador.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NovoProdutoRequest {

    @NotBlank
    @UniqueValue(Classe = Produto.class, campo = "nome")
    private String nome;
    @NotNull
    @Min(0) @Digits(integer = 8, fraction = 2)
    @Positive
    private BigDecimal valor;
    @Min(0)
    @NotNull
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @CheckIfExist(domain = Categoria.class, field = "id")
    private Long idCategoria;
    @Size(min = 3)
    @Valid
    @UniqueElements
    private List<CaracteristicasRequest> caracteristicas = new ArrayList<>();


    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao,
                              Long idCategoria, @Valid List<CaracteristicasRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario){
        Optional<Categoria> categoria = categoriaRepository.findById(this.idCategoria);
        Assert.isTrue(categoria.isPresent(), "Não existe uma categoria com id " + this.idCategoria);

        Produto produto = new Produto(this.nome, this.valor, this.quantidade, this.descricao,
                categoria.get(), usuario, this.caracteristicas);

        return produto;
    }




}