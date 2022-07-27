package example.example.behavior.observer.classic;

public class ConcretObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ConcretObserverTwo is notified.");
    }
}
