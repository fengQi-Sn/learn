package example.example.behavior.observer.asynchronous;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObserverAction {
    //观察者类
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = target;
        this.method = method;
        this.method.setAccessible(true);
    }

    // event是method方法的参数
    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
