package br.com.alura.project_challenge_literalura.banco;

public class BancoDados {
    public void listarLivros() {
        System.out.println("Listando os livros do banco");
    }

    public void listarAutores() {
        System.out.println("Listando os autores do banco");
    }

    public void listarAutoresVivos(int ano) {
        System.out.println("Listando os autores vivos no ano " + ano);
    }

    public void listarLivrosPorIdioma(String idioma) {
        System.out.println("Listando os livros do idioma " + idioma);
    }
}
