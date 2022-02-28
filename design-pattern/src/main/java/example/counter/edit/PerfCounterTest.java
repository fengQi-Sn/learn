package example.counter.edit;


import example.counter.MetricsCollector;
import example.counter.db.MetricsStorage;
import example.counter.db.RedisMetricsStorage;
import example.counter.request.RequestInfo;

/**
 * 重构后MetricsStorage、Aggregator、StatViewer 三个类的设计也符合迪米特法则。它们只与跟自己有直接相关的数据进行交互。
 * MetricsStorage 输出的是 RequestInfo 相关数据。
 * Aggregator 类输入的是 RequestInfo 数据，输出的是 RequestStat 数据。
 * StatViewer 输入的是 RequestStat 数据
 *
 * 框架的使用方式暴露了太多细节给用户，过于灵活也带来了易用性的降低
 */
public class PerfCounterTest {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("wangzheng@xzg.com");
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据
        // 我们通过 MetricsCollector 类来收集接口的访问情况，这部分收集代码会跟业务逻辑代码耦合在一起，或者统一放到类似 Spring AOP 的切面中完成
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
