package example.example.structure.adapter.object;

public class Adaptor implements ITarget {
    private Adaptee adaptee;
    public Adaptor(Adaptee adaptee) { this.adaptee = adaptee; }

    @Override
    public void f1() {
        adaptee.fa();
    }

    @Override
    public void f2() {
        adaptee.fa();
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}
