package br.com.alura.project_challenge_literalura.menu;

import br.com.alura.project_challenge_literalura.service.BibliotecaService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final BibliotecaService service;

    public Menu(BibliotecaService service) {
        this.service = service;
    }

    public void mostrarMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\nEscolha o número da sua opção:");
            System.out.println("1 - Buscar livro pelo título (API Gutendex)");
            System.out.println("2 - Listar livros cadastrados");
            System.out.println("3 - Listar autores cadastrados");
            System.out.println("4 - Listar autores vivos em um ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> service.buscarLivroApi();
                case 2 -> service.listarLivros();
                case 3 -> service.listarAutores();
                case 4 -> service.listarAutoresVivos();
                case 5 -> service.listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
