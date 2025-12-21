package br.com.alura.project_challenge_literalura;

import br.com.alura.project_challenge_literalura.service.BibliotecaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ProjectChallengeLiteraluraApplication implements CommandLineRunner {

    private final BibliotecaService bibliotecaService;

    public ProjectChallengeLiteraluraApplication(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectChallengeLiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\nBem vindo ao LiterAlura");
            System.out.println("Escolha o número da sua opção:");
            System.out.println("----------------------------");
            System.out.println("1 - Buscar livro pelo título (API Gutendex)");
            System.out.println("2 - Listar livros cadastrados");
            System.out.println("3 - Listar autores cadastrados");
            System.out.println("4 - Listar autores vivos em um determinado ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("0 - Sair");

            String entrada = scanner.nextLine();
            int opcao;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números válidos!");
                continue;
            }

            switch (opcao) {
                case 1 -> bibliotecaService.buscarLivroApi();
                case 2 -> bibliotecaService.listarLivros();
                case 3 -> bibliotecaService.listarAutores();
                case 4 -> bibliotecaService.listarAutoresVivos();
                case 5 -> bibliotecaService.listarLivrosPorIdioma();
                case 0 -> {
                    System.out.println("Saindo...");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
