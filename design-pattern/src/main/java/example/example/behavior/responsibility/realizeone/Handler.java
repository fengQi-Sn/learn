package example.example.behavior.responsibility.realizeone;

public abstract class Handler {
    protected Handler successor = null;
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }
    protected abstract void handle();
}
