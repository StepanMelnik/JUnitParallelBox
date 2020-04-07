package com.sme.junit.parallel.process;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents module with a list of steps.
 */
public class Module
{
    private final List<ProcessStep> steps = new ArrayList<>();
    private final String name;

    public Module(String name)
    {
        this.name = name;
    }

    /**
     * Fetch module name.
     * 
     * @return Returns the name of module.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Add new step in the module.
     * 
     * @param processStep The step of module.
     */
    public void addProcessStep(ProcessStep processStep)
    {
        steps.add(processStep);
    }

    /**
     * Fetch all steps of the module.
     * 
     * @return Returns all steps.
     */
    public List<ProcessStep> getProcessSteps()
    {
        return unmodifiableList(steps);
    }
}
