import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class StickerCreator {

    public void create(InputStream inputStream, String archiveName) throws IOException {

        // leitura da imagem
        // FileInputStream inputStreeam = new FileInputStream(new
        // File("input/filme.jpg"));
        // inputStream = new URL(
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // cria nova imagem em memoria comt ranspoarecnaio e com tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        graphics.drawImage(originalImage, 0, 0, null);

        // escrever uma frase na nova imagem
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 80);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        graphics.drawString("TopZera", 0, newHeight - 20);
        // escrever a imagem nova em um arquivo
        ImageIO.write(newImage, "png", new File("output/" + archiveName));

    }
}
