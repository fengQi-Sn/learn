package example.example.behavior.observer.asynchronous.demo;

import example.example.behavior.observer.asynchronous.EventBus;

public class Demo {
    private static EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        eventBus.register(new ObserverOne());
        eventBus.post(1);
    }
}
