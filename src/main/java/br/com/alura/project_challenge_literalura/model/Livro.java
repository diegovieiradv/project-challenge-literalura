package br.com.alura.project_challenge_literalura.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String titulo;

    private String idioma;
    private int downloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, Autor autor, String idioma, int downloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.downloads = downloads;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getDownloads() {
        return downloads;
    }

    @Override
    public String toString() {
        return """
        --- LIVRO ---
        Título: %s
        Idioma: %s
        Downloads: %d
        Autor: %s
        Nascimento: %s
        Falecimento: %s
        """.formatted(
                titulo,
                idioma,
                downloads,
                autor != null ? autor.getNome() : "Desconhecido",
                autor != null ? autor.getAnoNascimento() : "N/A",
                autor != null ? autor.getAnoFalecimento() : "N/A"
        );
    }
}
