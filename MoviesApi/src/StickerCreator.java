import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;

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

        var font = new Font(Font.SANS_SERIF, Font.ITALIC, 80);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);

        String text = "Topzera";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        var stringBounds = fontMetrics.getStringBounds(text, graphics);

        int widthAlignText = width - (width / 2) - (int) stringBounds.getWidth() / 2;
        int heightAlignText = newHeight - (newHeight - height) / 2;

        graphics.drawString(text, widthAlignText, heightAlignText);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(text, font, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(widthAlignText, heightAlignText);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.WHITE);
        graphics.draw(outline);

        ImageIO.write(newImage, "png", new File("output/" + archiveName.replace(':', '-')));

    }
}
