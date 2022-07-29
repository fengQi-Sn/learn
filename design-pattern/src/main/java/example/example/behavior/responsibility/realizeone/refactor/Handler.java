package example.example.behavior.responsibility.realizeone.refactor;

public abstract class Handler {
    protected Handler successor = null;
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }

    //利用模板模式，将调用 successor.handle() 的逻辑从具体的处理器类中剥离出来，放到抽象父类中
    public final void handle() {
        boolean handled = doHandle();
        if (successor != null && !handled) { successor.handle(); }
    }

    protected abstract boolean doHandle();
}
