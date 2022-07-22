package example.example.structure.decorator;

import java.io.*;

public class IoLearn {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("");
        //对于不需要增加缓存功能的函数来说，BufferedInputStream还是必须把它重新实现一遍，简单包裹对InputStream对象的函数调用。
        //如果不重新实现，那BufferedInputStream类就无法将最终读取数据的任务，委托给传递进来的 InputStream 对象来完成

        //最终读取任务是in完成的
        InputStream bin = new BufferedInputStream(in);
        DataInputStream din = new DataInputStream(bin);
        int data = din.readInt();
    }
}
