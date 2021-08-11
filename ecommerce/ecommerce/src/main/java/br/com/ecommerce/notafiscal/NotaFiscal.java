package br.com.ecommerce.notafiscal;

import br.com.ecommerce.finalizacomprapart2.EventoPedido;
import br.com.ecommerce.produto.pedido.Pedido;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class NotaFiscal implements EventoPedido {
    @Override
    public void processa(Pedido pedido) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of(
                "idPedido", pedido.getId(), "idUsuario", pedido.getUsuario().getId());

        HttpEntity<Map> entity = new HttpEntity<Map>(request, retornarHeader());
        restTemplate.postForEntity("http://localhost:8080/notafiscal", entity, String.class);
    }
    public HttpHeaders retornarHeader() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIgQXBpIGRvIE1lcmNhZG8gTGl2cmUgIiwic3ViIjoiMSIsImlhdCI6MTYyODcxMDYzMSwiZXhwIjoxNjI4NzE5MjcxfQ.37lbPJDgF_z7xLy_RxHDcHGR9Y4tNb9RPIwhPa-jcCI";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        return headers;
    }
}
