package example.example.creation.factory.method.factory;

import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.JsonRuleConfigParser;

public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}
