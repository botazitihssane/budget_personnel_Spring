package ma.emsi.budget.model;

import java.time.Month;
import java.time.Year;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Budget")
public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Month mois;
	private Year annee;
	@ManyToOne
	private Utilisateur utilisateur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Month getMois() {
		return mois;
	}

	public void setMois(Month mois) {
		this.mois = mois;
	}

	public Year getAnnee() {
		return annee;
	}

	public void setAnnee(Year annee) {
		this.annee = annee;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Budget(int id, Month mois, Year annee, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.mois = mois;
		this.annee = annee;
		this.utilisateur = utilisateur;
	}

	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}

}
