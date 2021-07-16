package br.com.ecommerce.produto.imagem;

public class ImagemResponse {

    private String link;

    public ImagemResponse(Imagem imagem) {
        this.link = imagem.getLink();
    }

    public String getLink() {
        return link;
    }
}