package example.counter.edit;

import example.counter.request.RequestInfo;
import example.counter.request.RequestStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据原始数据，计算得到统计数据。我们可以将这部分逻辑移动到 Aggregator 类中。
 * 这样 Aggregator 类就不仅仅是只包含统计方法的工具类了
 *
 * Aggregator 类从一个只包含一个静态函数的工具类，变成了一个普通的聚合统计类。现在，我们可以通过依赖注入的方式，将其组装进 ConsoleReporter 和 EmailReporter 类中，这样就更加容易编写单元测试
 * 如果随着更多的统计功能的加入，Aggregator 类的代码会越来越多。这个时候，我们可以将统计函数剥离出来，设计成独立的类，以解决 Aggregator 类的无限膨胀问题。不过，暂时来说没有必要这么做，毕竟将每个统计函数独立成类，会增加类的个数，也会影响到代码的可读性和可维护性
 */
public class Aggregator {
    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis/1000));
        return requestStat;
    }

    // 以下的函数的代码实现均省略...
    private double max(List<Double> dataset) {return 0L;}
    private double min(List<Double> dataset) {return 0L;}
    private double avg(List<Double> dataset) {return 0L;}
    private double tps(int count, double duration) {return 0L;}
    private double percentile999(List<Double> dataset) {return 0L;}
    private double percentile99(List<Double> dataset) {return 0L;}
    private double percentile(List<Double> dataset, double ratio) {return 0L;}
}

