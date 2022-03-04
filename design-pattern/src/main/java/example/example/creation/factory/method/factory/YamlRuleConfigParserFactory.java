package example.example.creation.factory.method.factory;

import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.YamlRuleConfigParser;

public class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}
