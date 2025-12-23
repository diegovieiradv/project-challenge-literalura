package br.com.alura.project_challenge_literalura.service;

import br.com.alura.project_challenge_literalura.model.Autor;
import br.com.alura.project_challenge_literalura.model.Livro;
import br.com.alura.project_challenge_literalura.repository.AutorRepository;
import br.com.alura.project_challenge_literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class BibliotecaService {

    private final LivroRepository livroRepo;
    private final AutorRepository autorRepo;
    private final Scanner scanner = new Scanner(System.in);
    private final GutendexApi gutendexApi = new GutendexApi();

    public BibliotecaService(LivroRepository livroRepo, AutorRepository autorRepo) {
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
    }

    public Livro buscarLivroApi() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        Livro livro = gutendexApi.buscarLivro(titulo);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return null;
        }

        if (livroRepo.existsByTituloIgnoreCase(livro.getTitulo())) {
            System.out.println("Esse livro já está cadastrado.");
            return null;
        }

        System.out.println("""
      ------- LIVRO -------
      Título: %s
      Autor: %s
      Idioma: %s
      Downloads: %d
      ---------------------
    """.formatted(
                livro.getTitulo(),
                livro.getAutor().getNome(),
                livro.getIdioma(),
                livro.getDownloads()
        ));

        Autor autorExistente = autorRepo.findByNomeIgnoreCase(livro.getAutor().getNome());
        if (autorExistente == null) {
            autorExistente = autorRepo.save(livro.getAutor());
        }

        Livro livroSalvar = new Livro(
                livro.getTitulo(),
                autorExistente,
                livro.getIdioma(),
                livro.getDownloads()
        );

        livroRepo.save(livroSalvar);
        System.out.println("Livro salvo com sucesso.");

        return livroSalvar;
    }


    public void listarLivros() {
        var livros = livroRepo.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println("""
            ------- LIVRO -------
            Título: %s
            Autor: %s
            Idioma: %s
            Numero de downloads: %d
            ---------------------
            """.formatted(
                    livro.getTitulo(),
                    livro.getAutor().getNome(),
                    livro.getIdioma(),
                    livro.getDownloads()
            ));
        }
    }

    public void listarAutores() {
        var autores = autorRepo.findAll();

        for (Autor autor : autores) {
            System.out.println("--------------------------");
            System.out.println("Autor: " + autor.getNome());
            System.out.println("Nascimento: " + autor.getAnoNascimento());
            System.out.println("Falecimento: " + autor.getAnoFalecimento());

            if (autor.getLivros() == null || autor.getLivros().isEmpty()) {
                System.out.println("Livros: []");
            } else {
                System.out.print("Livros: [ ");

                for (int i = 0; i < autor.getLivros().size(); i++) {
                    System.out.print(autor.getLivros().get(i).getTitulo());

                    if (i < autor.getLivros().size() - 1) {
                        System.out.print(", ");
                    }
                }

                System.out.println(" ]");
            }
        }

        System.out.println("--------------------------");
    }

    public void listarAutoresVivos() {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        var autores = autorRepo.findAll().stream()
                .filter(a -> a.getAnoNascimento() != null && a.getAnoNascimento() <= ano)
                .filter(a -> a.getAnoFalecimento() == null || a.getAnoFalecimento() >= ano)
                .toList();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para esse ano.");
            return;
        }

        for (Autor autor : autores) {
            System.out.println("--------------------------");
            System.out.println("Autor: " + autor.getNome());
            System.out.println("Nascimento: " + autor.getAnoNascimento());
            System.out.println("Falecimento: " + autor.getAnoFalecimento());

            if (autor.getLivros() == null || autor.getLivros().isEmpty()) {
                System.out.println("Livros: []");
            } else {
                System.out.print("Livros: [ ");

                for (int i = 0; i < autor.getLivros().size(); i++) {
                    System.out.print(autor.getLivros().get(i).getTitulo());

                    if (i < autor.getLivros().size() - 1) {
                        System.out.print(", ");
                    }
                }

                System.out.println(" ]");
            }
        }

        System.out.println("--------------------------");
    }

    public void listarLivrosPorIdioma() {
        System.out.println("""
    Insira o idioma para realizar a busca:
    es - Espanhol
    en - Inglês
    fr - Francês
    pt - Português
    """);

        System.out.print("Idioma: ");
        String idioma = scanner.nextLine().toLowerCase();

        var livros = livroRepo.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println("""
        ------- LIVRO -------
        Título: %s
        Autor: %s
        Idioma: %s
        Numero de downloads: %d
        ---------------------
        """.formatted(
                    livro.getTitulo(),
                    livro.getAutor().getNome(),
                    livro.getIdioma(),
                    livro.getDownloads()
            ));
        }
    }
    public void buscarAutorPorNome() {
        scanner.nextLine();

        System.out.print("Digite o nome do autor: ");
        String nome = scanner.nextLine();

        var autores = autorRepo.findByNomeContainingIgnoreCase(nome);

        if (autores.isEmpty()) {
            System.out.println("Autor não encontrado.");
            return;
        }

        for (Autor autor : autores) {
            System.out.println("--------------------------");
            System.out.println("Autor: " + autor.getNome());
            System.out.println("Nascimento: " + autor.getAnoNascimento());
            System.out.println("Falecimento: " + autor.getAnoFalecimento());

            if (autor.getLivros() == null || autor.getLivros().isEmpty()) {
                System.out.println("Livros: []");
            } else {
                System.out.print("Livros: [ ");

                for (int i = 0; i < autor.getLivros().size(); i++) {
                    System.out.print(autor.getLivros().get(i).getTitulo());

                    if (i < autor.getLivros().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(" ]");
            }
        }
        System.out.println("--------------------------");
    }
}
