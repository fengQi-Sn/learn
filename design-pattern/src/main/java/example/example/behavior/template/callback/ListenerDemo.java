package example.example.behavior.template.callback;

import example.example.behavior.template.callback.example.ICallback;

public class ListenerDemo {

    public static void main(String[] args) {
        //从代码结构上来看，事件监听器很像回调，即传递一个包含回调函数（methodToCallback()）的对象给另一个函数。从应用场景上来看，它又很像观察者模式，即事先注册观察者（ICallback），当用户点击按钮的时候，发送点击事件给观察者，并且执行相应的 methodToCallback() 函数
        //我们前面讲到，回调分为同步回调和异步回调。这里的回调算是异步回调，我们往 setOnClickListener() 函数中注册好回调函数之后，并不需要等待回调函数执行。
        new ListenerDemo().SetOnClickListener(new ICallback() {
            @Override
            public void methodToCallback() {
                System.out.println("I am clicked.");
            }
        });
    }


    public void SetOnClickListener(ICallback ca) {

    }

}
