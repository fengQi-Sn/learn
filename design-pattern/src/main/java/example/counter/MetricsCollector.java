package example.counter;

import example.counter.db.MetricsStorage;
import example.counter.request.RequestInfo;
import org.apache.commons.lang3.StringUtils;

public class MetricsCollector {
    /**
     * 基于接口而非实现编程
     */
    private MetricsStorage metricsStorage;

    /**
     * 依赖注入
     */
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    /**
     * 用一个函数代替了最小原型中的两个函数
     */
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
