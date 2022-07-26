package example.example.structure.flyweight;

public class Demo {
    public static void main(String[] args) {

        //当通过自动装箱，也就是调用 valueOf() 来创建 Integer 对象的时候，如果要创建的 Integer 对象的值在 -128 到 127 之间，会从 IntegerCache 类中直接返回，否则才调用 new 方法创建
        Integer i1 = 56;
        Integer i2 = Integer.valueOf(56);
        Integer i3 = 129;
        Integer i4 = 129;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);


        //String 类利用享元模式来复用相同的字符串常量。JVM 会专门开辟一块存储区来存储字符串常量，这块存储区叫作“字符串常量池”
        //String 类的享元模式的设计，跟 Integer 类稍微有些不同。
        //Integer 类中要共享的对象，是在类加载的时候，就集中一次性创建好的。
        //但是，对于字符串来说，我们没法事先知道要共享哪些字符串常量，所以没办法事先创建好，只能在某个字符串常量第一次被用到的时候，存储到常量池中，当之后再用到的时候，直接引用常量池中已经存在的即可，就不需要再重新创建了。
        String s1 = "小争哥";
        String s2 = "小争哥";
        String s3 = new String("小争哥");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }


}
