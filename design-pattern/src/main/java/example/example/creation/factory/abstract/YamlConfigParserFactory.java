import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.YamlRuleConfigParser;
import parser.ISystemConfigParser;
import parser.YamlSystemConfigParser;

public class YamlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new YamlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new YamlSystemConfigParser();
    }
}
