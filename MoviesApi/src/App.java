import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BACKGROUND = "\u001B[105m";

    public static void main(String[] args) throws Exception {

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        var body = response.body();

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size());
        // System.out.println(listaDeFilmes.get(0));

        char star = '\u2B50';

        for (Map<String, String> filme : listaDeFilmes) {

            System.out.printf("\n Titulo: %s", filme.get("title"));
            System.out.printf("\n Poster: %s", filme.get("image"));
            System.out.printf("\n" + ANSI_BACKGROUND + "Classificação: %s", filme.get("imDbRating") + ANSI_RESET);
            var countMax = Float.parseFloat(filme.get("imDbRating"));
            int count = 0;
            while (count <= countMax) {
                System.out.printf("%c", star);
                count++;
            }

            InputStream inputStream = new URL(filme.get("image")).openStream();
            var stickerCreator = new StickerCreator();
            stickerCreator.create(inputStream, filme.get("title") + ".png");
            System.out.println();
        }
    }
}
