import java.io.InputStream;
import java.net.URL;

public class App {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BACKGROUND = "\u001B[105m";

    public static void main(String[] args) throws Exception {

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ContentExtractor extractor = new ImdbContentExtractor();

        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        // ContentExtractor extractor = new NasaExtractContent();

        ClientHttp client = new ClientHttp();
        String json = client.GetData(url);

        var contents = extractor.getContents(json);

        // char star = '\u2B50';

        for (Content content : contents) {

            // String urlImage = content.get("image");
            // String title = content.get("title");
            // String imdbRating = content.get("imDbRating");

            System.out.printf("\n Titulo: %s", content.getTitle());
            System.out.printf("\n Poster: %s", content.getUrlImage());

            // System.out.printf("\n" + ANSI_BACKGROUND + "Classificação: %s", imdbRating +
            // ANSI_RESET);
            // var countMax = Float.parseFloat(imdbRating);
            // int count = 0;
            // while (count <= countMax) {
            // System.out.printf("%c", star);
            // count++;
            // }

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            var stickerCreator = new StickerCreator();
            stickerCreator.create(inputStream, content.getTitle() + ".png");
            System.out.println();
        }
    }
}
