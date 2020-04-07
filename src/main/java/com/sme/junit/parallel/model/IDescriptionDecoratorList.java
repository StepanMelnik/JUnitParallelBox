package com.sme.junit.parallel.model;

import java.util.List;

/**
 * Interface to create a list of test decorators.
 */
public interface IDescriptionDecoratorList
{
    /**
     * Fetch a list of decorators.
     * 
     * @return Returns the list of {@link DescriptionDecorator}.
     */
    List<DescriptionDecorator> list();
}
