package tp.behavior.step;





import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

public class CarreSteps extends Steps {
	
	private int x ;
	
	@Given("x valant $valeur")
	public void givenXValant(@Named("valeur")int val){
		x=val;
		//System.out.println("*** x valant"+x);
	}
	
	@When("x multiplie par $valeur")
	public void lorsqueXestMulipliePar(@Named("valeur")int val){
		x=x*val;
		//System.out.println("*** lorsque x est multiplie par "+val);
	}
	
	@Then("x vaut normalement $attendue")
    public void xVautNormalement(@Named("attendue")int valeurAttendue){
		//System.out.println("*** x vaut normalement "+valeurAttendue);
		Assert.assertTrue(x==valeurAttendue);
	}
	
	

}
