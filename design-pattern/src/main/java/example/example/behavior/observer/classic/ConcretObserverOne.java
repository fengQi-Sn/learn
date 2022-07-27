package example.example.behavior.observer.classic;

public class ConcretObserverOne implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ConcreteObserverOne is notified.");
    }
}
