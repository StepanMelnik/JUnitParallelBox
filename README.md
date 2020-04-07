# JUnitParallelBox
There is a list of tools to run junit test methods or classes in parallel, but the solution does not allow to split a big process on small pieces and run them in parallel.

This project allows to split integration tests of a big project(process) on small steps and test them in parallel.

## Description
Let's say we have a big project described as xml, json, etc file.
We can split modules of the project by hand and run all the modules with depend on steps in parallel.

Also we can describe how to test a long process manually and run separated modules in parallel by especial junit runner. 

The application prepares a proper tree of {@link org.junit.runner.Description}, so unit tests will be properly shown in your favorite JUnit UI.

### Usage
Check example in ParallelTestSuite.java

ParallelTestSuite.java describes a {@link LongProcess} implementation with a list of modules.
Every module contains a list of steps.

ParallelTestSuite.java creates a list of {@link DescriptionDecorator} per module and runs unit tests of all modules in parallel. 