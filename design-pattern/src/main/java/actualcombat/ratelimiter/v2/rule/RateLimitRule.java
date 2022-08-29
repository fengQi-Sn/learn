package actualcombat.ratelimiter.v2.rule;


public interface RateLimitRule {
    ApiLimit getLimit(String appId, String api);
}