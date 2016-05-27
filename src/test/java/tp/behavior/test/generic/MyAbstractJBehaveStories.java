package tp.behavior.test.generic;

import java.net.URL;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;

public abstract class MyAbstractJBehaveStories  extends JUnitStories {
	 
		public MyAbstractJBehaveStories() {
			super();
			//global jbehave settings:
			this.configuredEmbedder().embedderControls()
			     .doGenerateViewAfterStories(true)
			     .doIgnoreFailureInStories(false)
			     .doIgnoreFailureInView(false)
			     .doVerboseFailures(true);
		}
		
		@Override
	    public Configuration configuration() {
	        return new MostUsefulConfiguration()
	        	//fail if pending / not mapped with java
	            .usePendingStepStrategy(new FailingUponPendingStep()) 
	            // where to find java class with storyPaths() method to find the stories:
	            .useStoryLoader(new LoadFromClasspath(this.getClass()))  
	            // CONSOLE and TXT reporting
	            .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats()
	            		                     .withFormats(Format.CONSOLE, Format.TXT)
	            		                     .withFailureTrace(true)
	            		                     .withFailureTraceCompression(true) 
	            						); 
	    }
				
		
		@Override
		protected List<String> storyPaths() {
			//return Arrays.asList("stories/s1.story","stories/s2.story");
			URL searchInURL = CodeLocations.codeLocationFromClass(this.getClass()); 
		    return new StoryFinder().findPaths(searchInURL, "**/*.story", ""); 
		}
	}