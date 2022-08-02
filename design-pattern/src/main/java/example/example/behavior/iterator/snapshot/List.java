package example.example.behavior.iterator.snapshot;

public interface List<E> {
    void add(E obj);
    void remove(E obj);

    Iterator<E> iterator();
}
