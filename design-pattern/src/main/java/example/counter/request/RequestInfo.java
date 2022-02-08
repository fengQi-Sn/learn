package example.counter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dz0400820
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = -2864301045953659533L;
    private String apiName;
    private double responseTime;
    private long timestamp;
}
