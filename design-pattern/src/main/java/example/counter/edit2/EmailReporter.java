package example.counter.edit2;

import com.google.common.annotations.VisibleForTesting;
import example.counter.db.MetricsStorage;
import example.counter.db.RedisMetricsStorage;
import example.counter.edit.Aggregator;
import example.counter.edit.EmailViewer;
import example.counter.edit.StatViewer;

import java.util.*;


public class EmailReporter extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public EmailReporter(List<String> emailToAddresses) {
        super(new RedisMetricsStorage(), new Aggregator(), new EmailViewer());
        //this(new RedisMetricsStorage(), new Aggregator(), new EmailViewer(emailToAddresses));
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    //虽然涉及多线程不好写单元测试，但这段代码足够简单，简单到 bug 无处隐藏，那我们就没必要为了写单元测试而写单元测试
    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                doStatAndReport(startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    // 设置成protected而非private是为了方便写单元测试
    //可测试性仍然不好，因为它强依赖当前的系统时间
    @VisibleForTesting
    protected Date trimTimeFieldsToZeroOfNextDay() {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //一般的解决方法是，将强依赖的部分通过参数传递进来，这有点类似我们之前讲的依赖注入
    protected Date trimTimeFieldsToZeroOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.setTime(date); // 重新设置时间
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
