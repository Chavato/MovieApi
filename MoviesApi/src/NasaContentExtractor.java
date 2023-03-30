import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> getContents(String json) {

        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<Content>();
        for (Map<String, String> attribute : attributesList) {
            contents.add(new Content(attribute.get("title"), attribute.get("url")));
        }
        return contents;
    }
}
