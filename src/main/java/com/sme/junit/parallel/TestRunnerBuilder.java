package com.sme.junit.parallel;

import static java.util.Collections.unmodifiableList;
import static org.apache.commons.lang3.Validate.isAssignableFrom;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.runner.Runner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.sme.junit.parallel.model.IDescriptionDecoratorList;

/**
 * {@link RunnerBuilder} implementation to create a list of runners based on {@link IDescriptionDecoratorList#list()} data.
 */
public class TestRunnerBuilder extends RunnerBuilder
{
    /**
     * Returns null to create {@see this#runners(Class, Class[])}.
     */
    @Override
    public Runner runnerForClass(Class<?> testClass) throws Throwable
    {
        return null;
    }

    @Override
    public List<Runner> runners(Class<?> parent, Class<?>[] children) throws InitializationError
    {
        IDescriptionDecoratorList descriptionDecoratorList = createDescriptionDecoratorList(parent);
        List<Runner> runners = descriptionDecoratorList.list().stream().map(descriptionDecorator -> new SuiteRunner(descriptionDecorator)).collect(Collectors.toList());
        return unmodifiableList(runners);
    }

    private IDescriptionDecoratorList createDescriptionDecoratorList(Class<?> parent) throws InitializationError
    {
        isAssignableFrom(IDescriptionDecoratorList.class, parent, String.format(parent + " class must implemet IDescriptionDecoratorList"));
        try
        {
            return (IDescriptionDecoratorList) parent.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            throw new InitializationError(String.format("Cannot create IDescriptionDecoratorList implementation from %s class", parent));
        }
    }
}
