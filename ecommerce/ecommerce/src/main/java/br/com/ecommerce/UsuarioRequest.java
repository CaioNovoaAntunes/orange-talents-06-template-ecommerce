package br.com.ecommerce;

import br.com.ecommerce.validador.ExisteEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


    public class UsuarioRequest {


        @JsonProperty("login")
        @ExisteEmail(message = "existe já")
        @NotBlank
        @Email(message = "O login deve estar no formato de email")
        private String login;
        @NotBlank

        @JsonProperty("senha")
        @Size(min = 6)
        private String senha;

        public Usuario toModelo(){
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            return new Usuario(login,encoder.encode(senha));
        }
    }
