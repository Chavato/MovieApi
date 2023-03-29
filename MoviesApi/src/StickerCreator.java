import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class StickerCreator {

    public void create(InputStream inputStream, String archiveName) throws IOException {

        BufferedImage originalImage = ImageIO.read(inputStream);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        graphics.drawImage(originalImage, 0, 0, null);

        var font = new Font(Font.SANS_SERIF, Font.BOLD, 80);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        graphics.drawString("TopZera", width / 2, newHeight - 100);

        ImageIO.write(newImage, "png", new File("output/" + archiveName.replace(':', '-')));

    }
}
