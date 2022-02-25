package staticRelated;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class ImitateService {
    @Resource
    private ImitateMapper imitateMapper;
}
