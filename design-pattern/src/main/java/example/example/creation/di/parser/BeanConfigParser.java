package example.example.creation.di.parser;

import java.io.InputStream;
import java.util.List;

public interface BeanConfigParser {
    List parse(InputStream inputStream);
    List parse(String configContent);
}
