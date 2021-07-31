package br.com.ecommerce.produto.pedido;

import br.com.ecommerce.produto.Produto;
import br.com.ecommerce.produto.ProdutoRepository;
import br.com.ecommerce.produto.status.Status;
import br.com.ecommerce.usuario.Usuario;
import br.com.ecommerce.validador.CheckIfExist;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class PedidoRequest {

    private String metodoPagamento;
    @NotNull
    @CheckIfExist(domain = Produto.class, field = "id")
    private Long idProduto;
    @NotNull
    @Positive
    private Integer quantidade;

    public PedidoRequest(String metodoPagamento, Long idProduto, Integer quantidade) {

        this.metodoPagamento = metodoPagamento;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Pedido toModel(ProdutoRepository produtoRepository, Usuario usuario) {
        Optional<Produto> produto = produtoRepository.findById(idProduto);
        Assert.isTrue(produto.isPresent(), "Este produto não existe");

        return new Pedido(this.metodoPagamento, produto.get(), this.quantidade, usuario, Status.INICIADO);
    }



    public String getMetodoPagamento() {
        return metodoPagamento.toUpperCase();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}