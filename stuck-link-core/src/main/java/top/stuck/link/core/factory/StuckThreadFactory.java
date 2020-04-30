package top.stuck.link.core.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 2020-04-26
 *
 * @author Octopus
 */
public class StuckThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private ThreadGroup group;

    private final String NAME_PREFIX;

    public StuckThreadFactory(String name){
        SecurityManager securityManager = System.getSecurityManager();
        group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        NAME_PREFIX = name + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, NAME_PREFIX + threadNumber.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
