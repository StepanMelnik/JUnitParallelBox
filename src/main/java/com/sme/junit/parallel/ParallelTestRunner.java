package com.sme.junit.parallel;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

import com.sme.junit.parallel.model.IDescriptionDecoratorList;

/**
 * Parallel test runner to create a tree of unit tests and run them in parallel by scheduler.
 */
public class ParallelTestRunner extends Suite
{
    public ParallelTestRunner(Class<? extends IDescriptionDecoratorList> klass) throws InitializationError
    {
        super(klass, new TestRunnerBuilder().runners(klass, new Class<?>[] {}));
        setScheduler(new ParallelScheduler());
    }
}
