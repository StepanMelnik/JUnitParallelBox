package com.sme.junit.parallel;

import static java.util.concurrent.ForkJoinTask.inForkJoinPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;

import org.junit.runners.model.RunnerScheduler;

/**
 * Scheduler to run unit tests in parallel threads.
 */
class ParallelScheduler implements RunnerScheduler
{
    private static ForkJoinPool FORK_JOIN_POOL;

    static
    {
        ForkJoinPool.ForkJoinWorkerThreadFactory threadFactory = pool ->
        {
            ForkJoinWorkerThread thread = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            thread.setName("JUnit-" + thread.getName());
            return thread;
        };

        FORK_JOIN_POOL = new ForkJoinPool(Runtime.getRuntime().availableProcessors(), threadFactory, null, false);
    }

    private final List<ForkJoinTask<?>> asyncTasks = new LinkedList<>();
    private Runnable lastScheduledChild;

    @Override
    public void schedule(Runnable childStatement)
    {
        asyncTasks.add(inForkJoinPool() ? ForkJoinTask.adapt(childStatement).fork() : FORK_JOIN_POOL.submit(childStatement));
        lastScheduledChild = childStatement;
    }

    @Override
    public void finished()
    {
        if (lastScheduledChild != null)
        {
            for (ForkJoinTask<?> task : asyncTasks)
            {
                task.join();
            }
        }
    }
}
