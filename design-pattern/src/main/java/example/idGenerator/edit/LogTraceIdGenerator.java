package example.idGenerator.edit;

/**
 * @author dz0400820
 * 如果这个是类名，则过于通用，当出现新的打印日志的算法时不好起名
 *
 * 这个类名，对于扩展其他业务,也不合适，因为基于接口而非实现编程是为了灵活替换实现类，业务不同，没有替换的场景，没有意义（LogTraceIdGenerator、UserIdGenerator）
 */
public interface LogTraceIdGenerator extends IdGenerator {
}
