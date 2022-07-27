package example.example.behavior.observer.classic;

public class Demo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcretObserverOne());
        subject.registerObserver(new ConcretObserverTwo());
        subject.notifyObservers(new Message());
    }
}
