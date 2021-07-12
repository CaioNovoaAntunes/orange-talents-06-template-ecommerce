package br.com.ecommerce.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

   CategoriaRepository repository;
   @PersistenceContext
   EntityManager entityManager;
    @Autowired
    public CategoriaController(CategoriaRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @PostMapping
    public ResponseEntity<Categoria> categoriaCadastrar(@RequestBody @Valid CategoriaDTO categoriaDTO){
       Categoria categoria = categoriaDTO.toModel(entityManager);
      repository.save(categoria);
        return  ResponseEntity.ok(categoria);
    }


}
