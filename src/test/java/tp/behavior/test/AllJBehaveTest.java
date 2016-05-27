package tp.behavior.test;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import tp.behavior.step.CarreSteps;
import tp.behavior.step.CompteBehaviorSteps;
import tp.behavior.test.generic.MyAbstractJBehaveStories;

public class AllJBehaveTest  extends MyAbstractJBehaveStories /* JUnitStories*/ {
	 
		// Here we specify the steps classes
	    @Override
	    public InjectableStepsFactory stepsFactory() {        
	        // varargs, can have more that one steps classes
	        return new InstanceStepsFactory(configuration(),
	        		                        new CarreSteps() , 
	        		                        new CompteBehaviorSteps());
	    }

		
	}