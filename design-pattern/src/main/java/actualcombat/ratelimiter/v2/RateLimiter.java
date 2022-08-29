package actualcombat.ratelimiter.v2;


import actualcombat.ratelimiter.v2.alg.FixedTimeWinRateLimitAlg;
import actualcombat.ratelimiter.v2.alg.RateLimitAlg;
import actualcombat.ratelimiter.v2.datasource.FileRuleConfigSource;
import actualcombat.ratelimiter.v2.datasource.RuleConfigSource;
import actualcombat.ratelimiter.v2.rule.ApiLimit;
import actualcombat.ratelimiter.v2.rule.RateLimitRule;
import actualcombat.ratelimiter.v2.rule.RuleConfig;
import actualcombat.ratelimiter.v2.rule.TrieRateLimitRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);
    // 为每个api在内存中存储限流计数器
    private ConcurrentHashMap<String, RateLimitAlg> counters = new ConcurrentHashMap<>();
    private RateLimitRule rule;

    public RateLimiter() {
        //改动主要在这里：调用RuleConfigSource类来实现配置加载
        RuleConfigSource configSource = new FileRuleConfigSource();
        RuleConfig ruleConfig = configSource.load();
        this.rule = new TrieRateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) {
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if (apiLimit == null) {
            return true;
        }

        // 获取api对应在内存中的限流计数器（rateLimitCounter）
        String counterKey = appId + ":" + apiLimit.getApi();
        RateLimitAlg rateLimitCounter = counters.get(counterKey);
        if (rateLimitCounter == null) {
            RateLimitAlg newRateLimitCounter = new FixedTimeWinRateLimitAlg(apiLimit.getLimit());
            rateLimitCounter = counters.putIfAbsent(counterKey, newRateLimitCounter);
            if (rateLimitCounter == null) {
                rateLimitCounter = newRateLimitCounter;
            }
        }

        // 判断是否限流
        return rateLimitCounter.tryAcquire();
    }
}
