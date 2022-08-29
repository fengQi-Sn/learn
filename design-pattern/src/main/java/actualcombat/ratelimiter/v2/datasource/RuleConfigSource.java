package actualcombat.ratelimiter.v2.datasource;

import actualcombat.ratelimiter.v2.rule.RuleConfig;

public interface RuleConfigSource {
    RuleConfig load();
}
