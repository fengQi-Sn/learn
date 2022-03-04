import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.JsonRuleConfigParser;
import parser.ISystemConfigParser;
import parser.JsonSystemConfigParser;

public class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}
