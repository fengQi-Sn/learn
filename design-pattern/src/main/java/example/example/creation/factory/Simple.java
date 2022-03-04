package example.example.creation.factory;

import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.JsonRuleConfigParser;
import example.example.creation.factory.method.parser.YamlRuleConfigParser;

/**
 * 实际上，如果 if 分支并不是很多，代码中有 if 分支也是完全可以接受的。
 * 应用多态或设计模式来替代 if 分支判断逻辑，也并不是没有任何缺点的，它虽然提高了代码的扩展性，更加符合开闭原则，但也增加了类的个数，牺牲了代码的可读性
 */
public class Simple {
    public static IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = new YamlRuleConfigParser();
        }
        return parser;
    }
}
