package br.com.alura.project_challenge_literalura.service;

import br.com.alura.project_challenge_literalura.gutendex.GutendexResponse;
import br.com.alura.project_challenge_literalura.gutendex.LivroApi;
import br.com.alura.project_challenge_literalura.model.Autor;
import br.com.alura.project_challenge_literalura.model.Livro;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GutendexApi {

    public Livro buscarLivro(String titulo) {

        try {
            String tituloFormatado = titulo.replace(" ", "%20");
            String urlString = "https://gutendex.com/books/?search=" + tituloFormatado;

            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream())
            );

            StringBuilder json = new StringBuilder();
            String linha;

            while ((linha = leitor.readLine()) != null) {
                json.append(linha);
            }

            leitor.close();

            Gson gson = new Gson();
            GutendexResponse resposta =
                    gson.fromJson(json.toString(), GutendexResponse.class);

            if (resposta.getResults().isEmpty()) {
                return null;
            }

            LivroApi livroApi = resposta.getResults().get(0);

            // Autor
            Autor autor = null;
            if (!livroApi.getAuthors().isEmpty()) {
                var autorApi = livroApi.getAuthors().get(0);
                autor = new Autor(
                        autorApi.getName(),
                        autorApi.getBirth_year(),
                        autorApi.getDeath_year()
                );
            }

            // Livro do MODEL
            return new Livro(
                    livroApi.getTitle(),
                    autor,
                    livroApi.getLanguages().get(0),
                    livroApi.getDownload_count()
            );

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro na API");
            e.printStackTrace();
            return null;
        }
    }
}
