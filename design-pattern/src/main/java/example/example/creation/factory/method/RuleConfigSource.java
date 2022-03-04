package example.example.creation.factory.method;


import example.example.creation.factory.method.factory.IRuleConfigParserFactory;
import example.example.creation.factory.method.factory.JsonRuleConfigParserFactory;
import example.example.creation.factory.method.parser.RuleConfig;
import example.example.creation.factory.method.factory.YamlRuleConfigParserFactory;
import example.example.creation.factory.method.parser.IRuleConfigParser;

/**
 * 工厂类对象的创建逻辑又耦合进了 load() 函数中，跟我们最初的代码版本非常相似，引入工厂方法非但没有解决问题，反倒让设计变得更加复杂了
 *
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        IRuleConfigParserFactory parserFactory = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new JsonRuleConfigParserFactory();
        }  else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new YamlRuleConfigParserFactory();
        }  else {
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
