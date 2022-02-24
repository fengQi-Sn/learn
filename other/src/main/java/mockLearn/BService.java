package mockLearn;

import com.alibaba.dubbo.config.annotation.Reference;
import mockLearn.reference.ReferenceService;

public class BService {
    @Reference(parameters = {"method1.timeout", "500", "method2.timeout", "500"})
    private ReferenceService referenceInterface;
}
