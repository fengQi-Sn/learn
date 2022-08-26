package opensource.spring;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {
    private String message;

    public DemoEvent(Object source, String message) {
        super(source);
    }

    public String getMessage() {
        return message;
    }
}
