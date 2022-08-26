package opensource.guava;

import com.google.common.collect.ForwardingCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * AddLoggingCollection 是基于代理模式实现的一个代理类，它在原始 Collection 类的基础之上，针对“add”相关的操作，添加了记录日志的功能。
 * 代理模式、装饰器、适配器模式可以统称为 Wrapper 模式，通过 Wrapper 类二次封装原始类。它们的代码实现也很相似，都可以通过组合的方式，将 Wrapper 类的函数实现委托给原始类的函数来实现
 * 这个 ForwardingCollection 类是一个“默认 Wrapper 类”或者叫“缺省 Wrapper 类”。这类似于在装饰器模式那一节课中，讲到的 FilterInputStream 缺省装饰器类
 * 不使用这个 ForwardinCollection 类，而是让 AddLoggingCollection 代理类直接实现 Collection 接口，那 Collection 接口中的所有方法，都要在 AddLoggingCollection 类中实现一遍，而真正需要添加日志功能的只有 add() 和 addAll() 两个函数，其他函数的实现，都只是类似 Wrapper 类中 f2() 函数的实现那样，简单地委托给原始 collection 类对象的对应函数
 * 用户在实现自己的 Wrapper 类的时候，基于缺省的 Forwarding 类来扩展，就可以只实现自己关心的方法，其他不关心的方法使用缺省 Forwarding 类的实现，就像 AddLoggingCollection 类的实现那样。
 */
public class AddLoggingCollection<E> extends ForwardingCollection<E> {
    private static final Logger logger = LoggerFactory.getLogger(AddLoggingCollection.class);
    private Collection<E> originalCollection;

    public AddLoggingCollection(Collection<E> originalCollection) {
        this.originalCollection = originalCollection;
    }
    @Override
    protected Collection delegate() {
        return originalCollection;
    }

    @Override
    public boolean add(E element) {
        logger.info("Add element: " + element);
        return this.delegate().add(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        logger.info("Size of elements to add: " + collection.size());
        return this.delegate().addAll(collection);
    }
}
