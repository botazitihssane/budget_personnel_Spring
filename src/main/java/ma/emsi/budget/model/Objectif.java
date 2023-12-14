package ma.emsi.budget.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Objectif")
public class Objectif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Le titre de votre objectif ne peut pas être vide")
	private String titre;
	private String description;
	private double montantActuel;

	@NotNull(message = "La date limite ne peut pas être nulle")
	@Future(message = "La date limite doit être dans le futur")
	private LocalDate dateLimite;

	@Positive(message = "Le montant cible doit être positif")
	private double montantCible;

	@ManyToOne
	private Utilisateur utilisateur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(LocalDate dateLimite) {
		this.dateLimite = dateLimite;
	}

	public double getMontantCible() {
		return montantCible;
	}

	public void setMontantCible(double montantCible) {
		this.montantCible = montantCible;
	}

	public double getMontantActuel() {
		return montantActuel;
	}

	public void setMontantActuel(double montantActuel) {
		this.montantActuel = montantActuel;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Objectif(int id, String titre, String description, LocalDate dateLimite, double montantCible,
			double montantActuel, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateLimite = dateLimite;
		this.montantCible = montantCible;
		this.montantActuel = montantActuel;
		this.utilisateur = utilisateur;
	}

	public Objectif() {
		super();
	}

}
