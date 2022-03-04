package example.example.creation.factory;

import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.RuleConfig;

/**
 * 如果新增parser会修改工厂方法，不符合开闭原则，但如果修改不频繁，也可以接受
 */
public class RuleConfigSourceSimple {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = Simple.createParser(ruleConfigFileExtension);
        if (parser == null) {
            throw new RuntimeException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }

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
