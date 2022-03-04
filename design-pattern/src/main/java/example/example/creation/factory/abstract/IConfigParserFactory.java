import example.example.creation.factory.method.parser.IRuleConfigParser;
import parser.ISystemConfigParser;

public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();
    ISystemConfigParser createSystemParser();
    //此处可以扩展新的parser类型，比如IBizConfigParser
}
