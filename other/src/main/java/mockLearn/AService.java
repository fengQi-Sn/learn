package mockLearn;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import mockLearn.reference.ReferenceService;
import org.springframework.stereotype.Component;

@Component
public class AService {

    @Reference(parameters = {"method.timeout", "3000"})
    private ReferenceService referenceService;

    public void get1() {
        referenceService.method("1");
    }

    public String get2(String par) {
        referenceService.method(par);
        return "success";
    }
}
