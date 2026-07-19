package br.com.alura.project_challenge_literalura.gutendex;

import java.util.List;

public class LivroApi {

    private String title;
    private List<AutorApi> authors;
    private List<String> languages;
    private int download_count;

    public String getTitle() {
        return title;
    }

    public List<AutorApi> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public int getDownload_count() {
        return download_count;
    }
}