package br.com.alura.project_challenge_literalura.repository;

import br.com.alura.project_challenge_literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByIdioma(String idioma);

    boolean existsByTituloIgnoreCase(String titulo);
}
