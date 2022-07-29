package example.example.behavior.responsibility.realizetwo;

public class Application {
    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.handle();
    }
}
