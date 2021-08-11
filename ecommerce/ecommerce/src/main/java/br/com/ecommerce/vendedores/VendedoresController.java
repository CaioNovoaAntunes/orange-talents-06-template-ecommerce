package br.com.ecommerce.vendedores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendedoresController {

    @PostMapping("/ranking")
    public void rankingVendedores(@RequestBody RankingRequest request) {

        System.out.println(request.toString());
    }
}
