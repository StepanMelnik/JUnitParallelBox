package com.sme.junit.parallel.model;

/**
 * Testable marker of a step in long process.
 */
public interface ITestable
{
    /**
     * Perform test logic.
     */
    void test();
}
