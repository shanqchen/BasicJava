package basic.java.NIO.netty.ch2.pseudoAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

    private ExecutorService executor;

    //由于线程池和消息队列都是有界的，因此无论客户端并发数量多大，它都不会导致线程个数过于膨胀或者内存溢出，相比传统的一连接一线程模型，是一种改良
    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<java.lang.Runnable>(queueSize));
    }
    
    public void execute(java.lang.Runnable task){
        executor.execute(task);
    }
}
