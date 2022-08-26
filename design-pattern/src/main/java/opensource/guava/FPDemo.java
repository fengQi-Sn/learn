package opensource.guava;


import java.util.Optional;
import java.util.stream.Stream;

/**
 * Java 为函数式编程引入了三个新的语法概念：Stream 类、Lambda 表达式和函数接口（Functional Inteface）
 * Stream 类用来支持通过“.”级联多个函数操作的代码编写方式；
 * 引入 Lambda 表达式的作用是简化代码编写；
 * 函数接口的作用是让我们可以把函数包裹成函数接口，来实现把函数当做参数一样来使用（Java 不像 C 一样支持函数指针，可以把函数直接当参数来使用）。
 */
public class FPDemo {
    public static void main(String[] args) {
        Optional<Integer> result = Stream.of("f", "ba", "hello")
                .map(s -> s.length())
                .filter(l -> l <= 3)
                .max((o1, o2) -> o1-o2);
        System.out.println(result.get()); // 输出2
    }
}