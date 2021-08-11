package br.com.ecommerce.notafiscal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotaFiscalController {

    @PostMapping("/notafiscal")
    public void processaNotaFiscal(@RequestBody NotaFiscalRequest request) {

        System.out.println(request.toString());
    }
}
