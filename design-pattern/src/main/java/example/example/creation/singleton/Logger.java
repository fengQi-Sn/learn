package example.example.creation.singleton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 类级别锁、分布式锁、并发队列、单例模式
 */
public class Logger {
    private FileWriter fileWriter;
    private static final Logger logger = new Logger();
    private Logger() {
        try{
            File file = new File("");
            this.fileWriter = new FileWriter(file, true);
        }catch (IOException e) {

        }
    }

    public static Logger getInstance() throws IOException {
        return logger;
    }

    public void log(String context) {
        try {
            fileWriter.write(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
