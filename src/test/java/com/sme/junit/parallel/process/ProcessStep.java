package com.sme.junit.parallel.process;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sme.junit.parallel.model.ITestable;

/**
 * Testable step of a process.
 */
public class ProcessStep implements ITestable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessStep.class);

    private final int id;
    private final String name;

    public ProcessStep(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public void test()
    {
        LOGGER.debug("Start test {} step in {} thread", name, Thread.currentThread());
        try
        {
            Thread.sleep(RandomUtils.nextLong(0l, 5000l));

            if (id == 5)
            {
                throw new RuntimeException("5 step is failed");
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        LOGGER.debug("Finish test {} step in {} thread", name, Thread.currentThread());
    }
}
