package example.example.behavior.visitor.refactor;

public abstract class ResourceFile {
    protected String filePath;
    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }
    abstract public void accept(Visitor vistor);
}
