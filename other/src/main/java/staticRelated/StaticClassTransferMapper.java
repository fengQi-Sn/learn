package staticRelated;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class StaticClassTransferMapper {
    /**
     * 如果这样直接调用就会有问题
     * A component required a bean of type 'imitateMapper' that could not be found.
     *
     */
    @Resource
    private ImitateMapper imitateMapper;
    private static StaticClassTransferMapper staticClassTransferMapper;

    @PostConstruct
    public void  update1() {
        staticClassTransferMapper = this;
        staticClassTransferMapper.imitateMapper = this.imitateMapper;
    }

    /**
     * 这样可以
     */
    @Resource
    private ImitateService imitateService;
    @PostConstruct
    public void  update2() {
        staticClassTransferMapper = this;
        staticClassTransferMapper.imitateService = this.imitateService;
    }
}
