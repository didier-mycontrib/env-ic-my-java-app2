package tp.domain.entity;

public class Compte {
	public static enum Status {OK, A_DECOUVERT };
	public static double DECOUVERT_AUTORISE = -300.0;
	
	private Long numero;
	private String label;
	private Double solde;
	private Status statut;
	
	public void debiter(double montant){
		double nouveauSolde = solde -montant;
		if(nouveauSolde >= DECOUVERT_AUTORISE){
			solde=nouveauSolde;
		}else 
			throw new RuntimeException("decouvert trop important non accepté");
	}
	/*
	//version avec mauvais comportement fonctionnel détecté par jbehave:
	public void debiter(double montant){
		solde = solde -montant;
	}
	*/
	
	public void crediter(double montant){
		solde = solde +montant;
	}
	
	
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde="
				+ solde + ", statut=" + statut + "]";
	}

	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}
	public Status getStatut() {
		if(solde>=0)
			statut = Status.OK;
		else
			statut = Status.A_DECOUVERT;
		return statut;
	}
	/*
	public void setStatut(Status statut) {
		this.statut = statut;
	}*/
	
	

}
