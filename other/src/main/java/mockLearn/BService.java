package mockLearn;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 直接对@Reference修饰的对象mock会出问题，但是这样可以成功
 */
public class BService {
    private AService aService;
    @Autowired
    public void init(AService aService) {
        this.aService = aService;
    }

    public void testMethod() {
        aService.get1();
        aService.get2("");
    }

}
