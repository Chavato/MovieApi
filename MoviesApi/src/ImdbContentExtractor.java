import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor {
    public List<Content> getContents(String json) {

        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<Content>();
        for (Map<String, String> attribute : attributesList) {

            String title = attribute.get("title");
            String imageUrl = attribute.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var content = new Content(title, imageUrl);

            contents.add(content);
        }
        return contents;
    }
}
