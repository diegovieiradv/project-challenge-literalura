package br.com.alura.project_challenge_literalura.repository;

import br.com.alura.project_challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNome(String nome);
}
