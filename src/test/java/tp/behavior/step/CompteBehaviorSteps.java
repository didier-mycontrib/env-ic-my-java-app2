package tp.behavior.step;



import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import tp.domain.entity.Compte;


/* A priori (d'un point de vue pragmatique),
 * pas de différence entre :
 * Given ... When ... When ...
 *  et 
 * Given ... When ...  And ...
 * 
 * ni entre
 * Then .. Then ...
 *    et 
 * Then .. And ...
 * 
 * MAIS TOUS LES Mots clefs (Given , When , Then , And) doivent absolument figurer EN TOUT DEBUT de LIGNE
 * 
 * ===
 * Une seule instance de la classe "...Steps" est utilisée pour gérer tous les scénarios d'un même fichier .story
 * ===
 * logique "stateful" entre 2 steps d'un même scénario .
 */

public class CompteBehaviorSteps extends Steps {
	
	private Compte compte ;
	private RuntimeException eventuelleException;
	private double ancienSolde,nouveauSolde;
	
	private void dumpContext(String msg){
		System.out.println(this.toString()+": compte="+compte.toString() + " , msg=" + msg);
	}
	
		
	private void initialiserCompte(double solde){
		compte = new Compte(); compte.setNumero(123L); compte.setLabel("compte courant");
		compte.setSolde(solde);
	}
	
	@Given("solde=$solde positif")
	public void initialiserComptePositif(@Named("solde")double solde){
		Assert.assertTrue(solde>=0);
		initialiserCompte(solde);
	}
	
	@Given("solde=$solde superieur au decouvert autorise")
	public void initialiserCompteValide(@Named("solde")double solde){
		Assert.assertTrue(solde >= Compte.DECOUVERT_AUTORISE);
		initialiserCompte(solde);
	}
	
	
	
	@When("debiter(montant=$montant)")
	public void declencherDebit(@Named("montant")double montant){
		dumpContext("declencherDebit avec montant="+montant);
		eventuelleException=null;
		try {
			compte.debiter(montant);
		} catch (RuntimeException e) {
			eventuelleException= e;
		}
	}
	
	@When("avec montant=$montant>solde et solde-montant >= DECOUVERT_AUTORISE")
	public void avecDecouvertAutorise(@Named("montant")double montant){
		dumpContext("avecDecouvertAutorise avec montant debit="+montant);
		Assert.assertTrue(montant >compte.getSolde());
		Assert.assertTrue(compte.getSolde()-montant >= Compte.DECOUVERT_AUTORISE);
	}
	
	@When("avec 0< montant=$montant <=solde")
	public void avecSoldeSuperieurAuMontantPositif(@Named("montant")double montant){
		dumpContext("avecSoldeSuperieurAuMontantPositif avec montant debit="+montant);
		Assert.assertTrue(montant >0);
		Assert.assertTrue(compte.getSolde()>=montant );
	}
	
	
	
	@Then("debit accepte")
    public void constaterDebitAccepte(){
		Assert.assertTrue(eventuelleException==null);
		dumpContext("constaterDebitAccepte");
	}
	
	@Then("statut = A_DECOUVERT")
    public void constaterStatutADecouvert(){
		Assert.assertTrue(compte.getStatut() == Compte.Status.A_DECOUVERT);
		dumpContext("constaterStatutADecouvert");
	}
	
	@Then("statut = OK")
    public void constaterStatutOK(){
		Assert.assertTrue(compte.getStatut() == Compte.Status.OK);
		dumpContext("constaterStatutOK");
	}
	
	@When("debiter(montant=$montant) avec solde-montant < DECOUVERT_AUTORISE")
	public void declencherDebitAvecDecouvertNonAutorise(@Named("montant")double montant){
		Assert.assertTrue(compte.getSolde()-montant< Compte.DECOUVERT_AUTORISE);
		eventuelleException=null;
		ancienSolde=compte.getSolde();
		try {
			compte.debiter(montant);
		} catch (RuntimeException e) {
			eventuelleException= e;
		}
		nouveauSolde=compte.getSolde();
	}
	
	
	@Then("debit refuse (exception)")
    public void constaterDebitRefuse(){
		Assert.assertTrue(eventuelleException!=null);
		dumpContext("constaterDebitRefuse");
	}
	@Then("solde inchange")
    public void constaterSoldeInchange(){
		Assert.assertTrue(nouveauSolde == ancienSolde);
		dumpContext("constaterSoldeInchange");
	}
	/*
	@BeforeScenario 
    public void inializeScenario() { 
		System.out.println("BeforeScenario callback called"); //exemple : ré-initialiser valeur en base
	 } 

	@AfterScenario 
	public void disposeScenario() {  
		System.out.println("AfterScenario callback called"); //exemple : libérer certaines ressources
	} */
	
	/* VERSION 1 (OLD) Sans AND :
	 * 
	@When("debiter(montant=$montant) avec montant>solde et solde-montant >= DECOUVERT_AUTORISE")
	public void declencherDebitAvecDecouvertAutorise(@Named("montant")double montant){
		Assert.assertTrue(montant >compte.getSolde());
		Assert.assertTrue(compte.getSolde()-montant >= Compte.DECOUVERT_AUTORISE);
		eventuelleException=null;
		try {
			compte.debiter(montant);
		} catch (RuntimeException e) {
			eventuelleException= e;
		}
	}	
	@Then("debit refuse (exception) ET solde inchange")
    public void constaterDecouvertNonAutorise(){
		Assert.assertTrue(eventuelleException!=null && nouveauSolde == ancienSolde);
	}
	
	@Then("debit accepte ET nouveau statut = A_DECOUVERT")
    public void constaterDecouvertAutorise(){
		Assert.assertTrue(eventuelleException==null && compte.getStatut() == Compte.Status.A_DECOUVERT);
	}	
	*/

}
