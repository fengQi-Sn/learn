package example.example.behavior.iterator;


import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<User> names = new ArrayList<>();
        names.add(new User("a"));
        names.add(new User("b"));
        names.add(new User("c"));
        names.add(new User("d"));

        List<User> xx = new ArrayList<>();
        xx.addAll(names);


        names.get(0).setName("edit");
        names.remove(2);

        xx.stream().forEach(key->{
            System.out.println(key.getName());
        });


    }



    public static void method() {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        Iterator<String> iterator1 = names.iterator();
        Iterator<String> iterator2 = names.iterator();
        iterator1.next();
        iterator1.remove();
        iterator2.next(); // 运行结果？
    }

    static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}