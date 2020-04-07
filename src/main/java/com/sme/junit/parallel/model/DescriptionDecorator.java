package com.sme.junit.parallel.model;

import java.util.HashMap;
import java.util.Map;

import org.junit.runner.Description;

/**
 * <p>
 * {@link Description} does not allow to override implementation, because uses private constructor.
 * </p>
 * <p>
 * The implementation decorates root suite description with children and connects all children descriptions to {@link ITestable} instance.
 * </p>
 */
public class DescriptionDecorator
{
    private final Map<Description, ITestable> suiteToDescription = new HashMap<>();
    private final Description rootSuiteDescription;

    public DescriptionDecorator(Description rootSuiteDescription)
    {
        this.rootSuiteDescription = rootSuiteDescription;
    }

    /**
     * Add {@link Description} with connected to {@link ITestable} implementation.
     * 
     * @param testDescription The {@link Description} instance;
     * @param testable The {@link ITestable} implementation.
     */
    public void addTestDescription(Description testDescription, ITestable testable)
    {
        rootSuiteDescription.addChild(testDescription);
        suiteToDescription.put(testDescription, testable);
    }

    /**
     * Fetch {@link ITestable} implementation by {@link Description}.
     * 
     * @param testDescription The unit test description;
     * @return Returns {@link ITestable} by key.
     */
    public ITestable getTestable(Description testDescription)
    {
        return suiteToDescription.get(testDescription);
    }

    /**
     * Fetch Root suite description.
     * 
     * @return Returns root description.
     */
    public Description getRootSuiteDescription()
    {
        return rootSuiteDescription;
    }

}
