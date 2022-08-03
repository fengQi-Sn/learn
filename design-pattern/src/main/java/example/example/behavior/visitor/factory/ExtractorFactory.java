package example.example.behavior.visitor.factory;

import java.util.HashMap;
import java.util.Map;

public class ExtractorFactory {
    private static final Map<ResourceFileType, Extractor> extractors = new HashMap<>();
    static {
        extractors.put(ResourceFileType.PDF, new PdfExtractor());
    }

    public static Extractor getExtractor(ResourceFileType type) {
        return extractors.get(type);
    }
}
