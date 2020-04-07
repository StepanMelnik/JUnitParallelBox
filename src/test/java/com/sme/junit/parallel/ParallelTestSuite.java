package com.sme.junit.parallel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.runner.Description;
import org.junit.runner.RunWith;

import com.sme.junit.parallel.model.DescriptionDecorator;
import com.sme.junit.parallel.model.IDescriptionDecoratorList;
import com.sme.junit.parallel.process.LongProcess;
import com.sme.junit.parallel.process.Module;
import com.sme.junit.parallel.process.ProcessStep;

import junit.framework.TestCase;

/**
 * Run steps of long process in parallel.
 */
@RunWith(ParallelTestRunner.class)
public class ParallelTestSuite implements IDescriptionDecoratorList
{
    @Override
    public List<DescriptionDecorator> list()
    {
        Module module1 = new Module("Module1");
        module1.addProcessStep(new ProcessStep(1, "Step1"));
        module1.addProcessStep(new ProcessStep(3, "Step3"));
        module1.addProcessStep(new ProcessStep(5, "Step5"));

        Module module2 = new Module("Module2");
        module2.addProcessStep(new ProcessStep(2, "Step2"));
        module2.addProcessStep(new ProcessStep(4, "Step4"));
        module2.addProcessStep(new ProcessStep(6, "Step6"));

        LongProcess longProcess = new LongProcess("LongProcess");
        longProcess.addModule(module1);
        longProcess.addModule(module2);

        return createDecorators(longProcess);
    }

    private List<DescriptionDecorator> createDecorators(LongProcess longProcess)
    {
        Stream.Builder<DescriptionDecorator> builder = Stream.builder();
        longProcess.getModules().forEach(module -> builder.accept(createDescriptionDecorator(module)));
        return builder.build().collect(Collectors.toList());
    }

    private DescriptionDecorator createDescriptionDecorator(Module module)
    {
        DescriptionDecorator descriptionDecorator = new DescriptionDecorator(Description.createSuiteDescription(module.getName()));
        module.getProcessSteps()
                .stream()
                .forEach(
                        step -> descriptionDecorator.addTestDescription(Description.createTestDescription(TestCase.class, step.getName()), step));
        return descriptionDecorator;
    }
}
