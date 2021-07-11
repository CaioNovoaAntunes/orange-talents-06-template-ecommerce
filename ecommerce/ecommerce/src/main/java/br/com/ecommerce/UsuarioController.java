package br.com.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity <Usuario> casatrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
       Usuario usuario =usuarioRequest.toModelo();
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

}
