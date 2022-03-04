package example.example.creation.factory.method;

import example.example.creation.factory.method.factory.IRuleConfigParserFactory;
import example.example.creation.factory.method.factory.RuleConfigParserFactoryMap;
import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.RuleConfig;
/**
 * 我们可以为工厂类再创建一个简单工厂，也就是工厂的工厂（RuleConfigParserFactoryMap），用来创建工厂类对象
 *
 * 当我们需要添加新的规则配置解析器的时候，我们只需要创建新的 parser 类和 parser factory 类，并且在 RuleConfigParserFactoryMap 类中，将新的 parser factory 对象添加到 cachedFactories 中即可。代码的改动非常少，基本上符合开闭原则
 */
public class RuleConfigSourceFactoryMap {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
            throw new RuntimeException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        IRuleConfigParser parser = parserFactory.createParser();

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}
