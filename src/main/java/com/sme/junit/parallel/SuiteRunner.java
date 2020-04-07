package com.sme.junit.parallel;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import com.sme.junit.parallel.model.DescriptionDecorator;

/**
 * Suite runner to run all unit tests step by step.
 */
public class SuiteRunner extends Runner
{
    private final DescriptionDecorator descriptionDecorator;

    public SuiteRunner(DescriptionDecorator descriptionDecorator)
    {
        this.descriptionDecorator = descriptionDecorator;
    }

    @Override
    public Description getDescription()
    {
        return descriptionDecorator.getRootSuiteDescription();
    }

    @Override
    public void run(RunNotifier notifier)
    {
        notifier.fireTestRunStarted(descriptionDecorator.getRootSuiteDescription());

        for (Description description : descriptionDecorator.getRootSuiteDescription().getChildren())
        {
            notifier.fireTestStarted(description);

            try
            {
                descriptionDecorator.getTestable(description).test();
            }
            catch (Exception e)
            {
                notifier.fireTestFailure(new Failure(description, e));
            }

            notifier.fireTestFinished(description);
        }
        notifier.fireTestRunFinished(new Result());
    }
}
