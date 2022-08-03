package example.example.behavior.visitor;


public class ParentClass {
    public void f() {
        System.out.println("I am ParentClass's f().");
    }
}
 class ChildClass extends ParentClass {
    @Override
    public void f() {
        System.out.println("I am ChildClass's f().");
    }
}
 class SingleDispatchClass {
    public void polymorphismFunction(ParentClass p) {
        p.f();
    }

    public void overloadFunction(ParentClass p) {
        System.out.println("I am overloadFunction(ParentClass p).");
        p.f();
    }

    public void overloadFunction(ChildClass c) {
        System.out.println("I am overloadFunction(ChildClass c).");
        c.f();
    }
}

class DemoMain {
    public static void main(String[] args) {
        SingleDispatchClass demo = new SingleDispatchClass();
        ParentClass p = new ChildClass();
        demo.polymorphismFunction(p);//执行哪个对象的方法，由对象的实际类型决定
        demo.overloadFunction(p);//执行对象的哪个方法，由参数对象的声明类型决定
    }
}
