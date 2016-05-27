package tp.behavior.test.single;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import tp.behavior.step.CarreSteps;
import tp.behavior.test.generic.MyAbstractJBehaveStories;

public class JBehaveTestCarre extends MyAbstractJBehaveStories /* JUnitStories*/ {
	 
		// Here we specify the steps classes
	    @Override
	    public InjectableStepsFactory stepsFactory() {        
	        // varargs, can have more that one steps classes
	        return new InstanceStepsFactory(configuration(), new CarreSteps());
	    }
	    
	    @Override // default is "stories/*.story" in MyAbstractJBehaveStories.storyPaths()
		protected List<String> storyPaths() {
			return Arrays.asList("stories/carre.story");//just one story here
		}

}