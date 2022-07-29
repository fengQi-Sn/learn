package example.example.behavior.responsibility.realizeone;


public class HandlerB extends Handler {
    @Override
    protected void handle() {
        boolean handled = false;
        //...
        if (!handled && successor != null) {
            successor.handle();
        }
    }
}
