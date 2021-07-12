package br.com.ecommerce.validador;


import br.com.ecommerce.Usuario;
import br.com.ecommerce.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExisteEmailValidator implements ConstraintValidator<ExisteEmail,String> {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Usuario> usuario=repository.findByLogin(login);
        return usuario.isEmpty();
    }
}