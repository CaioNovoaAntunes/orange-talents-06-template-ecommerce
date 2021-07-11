package br.com.ecommerce;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(nullable = false, unique = true)
    private String login;



    @Column(nullable = false)
    private String senha;

    @PastOrPresent
    private LocalDateTime criadoEm;


    public Usuario(@Email String login, String senha) {
        this.senha = senha;
        this.login = login;
        this.criadoEm = LocalDateTime.now();
    }
}
