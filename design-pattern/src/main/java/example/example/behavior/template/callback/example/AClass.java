package example.example.behavior.template.callback.example;

public class AClass {
    //同步回调的实现方式，在 process() 函数返回之前，执行完回调函数 methodToCallback()
    public static void main(String[] args) {
        BClass b = new BClass();
        b.process(new ICallback() {
            @Override
            public void methodToCallback() {
                System.out.println("call back me");
            }
        });
    }
}
