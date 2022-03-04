package example.example.creation.factory;

import example.example.creation.factory.method.parser.IRuleConfigParser;
import example.example.creation.factory.method.parser.JsonRuleConfigParser;
import example.example.creation.factory.method.parser.YamlRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果 parser 可以复用，为了节省内存和对象创建的时间，我们可以将 parser 事先创建好缓存起来
 */
public class SimpleCache {
    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("yaml", new YamlRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算
        }
        IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
        return parser;
    }
}
