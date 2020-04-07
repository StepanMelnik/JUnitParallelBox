package com.sme.junit.parallel.process;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a long process as a bunch of modules.
 */
public class LongProcess
{
    private final List<Module> modules = new ArrayList<>();
    private final String name;

    public LongProcess(String name)
    {
        this.name = name;
    }

    /**
     * Fetch process name.
     * 
     * @return Returns the name of process.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Add a new module in the process.
     * 
     * @param module The module of process.
     */
    public void addModule(Module module)
    {
        modules.add(module);
    }

    /**
     * Fetch all modules of the process.
     * 
     * @return Returns all modules.
     */
    public List<Module> getModules()
    {
        return unmodifiableList(modules);
    }
}
