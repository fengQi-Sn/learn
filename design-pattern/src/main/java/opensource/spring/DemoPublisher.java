package opensource.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {
    @Autowired
    private ApplicationContext applicationContext;

    //观察者需要事先注册到被观察者（JDK 的实现方式）或者事件总线（EventBus 的实现方式）中。那在 Spring 的实现中，把观察者注册到了 ApplicationContext 对象中。
    //ApplicationContext 这个类并不只是为观察者模式服务的。它底层依赖 BeanFactory（IOC 的主要实现类），提供应用启动、运行时的上下文信息，是访问这些信息的最顶层接口。
    public void publishEvent(DemoEvent demoEvent) {
        this.applicationContext.publishEvent(demoEvent);
    }


    public static void main(String[] args) {

    }
}
