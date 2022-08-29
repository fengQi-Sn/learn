package actualcombat.ratelimiter.v2.parser;

import actualcombat.ratelimiter.v2.rule.RuleConfig;

import java.io.InputStream;

public interface RuleConfigParser {
    RuleConfig parse(String configText);
    RuleConfig parse(InputStream in);
}
