package example.example.behavior.responsibility.realizeone;

//如果它能处理该请求，就不继续往下传递；如果不能处理，则交由后面的处理器来处理（也就是调用 successor.handle()）
public class HandlerA extends Handler {
    @Override
    protected void handle() {
        boolean handled = false;
        //...
        if (!handled && successor != null) {
            successor.handle();
        }
    }
}
