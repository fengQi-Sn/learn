package example.example.behavior.observer.asynchronous.demo;

import example.example.behavior.observer.asynchronous.Subscribe;

public class ObserverOne {
    @Subscribe
    public void method_1(String param) {
        System.out.println("method_1 is notified.");
    }


    @Subscribe
    public void method_2(Integer param) {
        System.out.println("method_2 is notified.");
    }
}
