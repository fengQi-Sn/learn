package example.counter.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dz0400820
 */
@Data
public class RequestStat implements Serializable {
    private static final long serialVersionUID = 3894417619715682491L;
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
}
