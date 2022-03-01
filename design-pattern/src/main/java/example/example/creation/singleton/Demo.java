package example.example.creation.singleton;

public class Demo {
    public static void main(String[] args) {
        System.out.println(IdGeneratorEnum.INSTANCE.getId());
        System.out.println(IdGeneratorEnum.INSTANCE.getId());
    }

    public Long method(String param, IdGeneratorEnum idGeneratorEnum) {
        return idGeneratorEnum.getId();
    }
}
