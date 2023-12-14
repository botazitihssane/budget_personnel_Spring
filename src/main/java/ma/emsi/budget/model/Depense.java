package ma.emsi.budget.model;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("Depense")
public class Depense extends Transaction {
	@NotBlank(message = "Le libellé ne peut pas être vide")
	private String libelle;
	private String description;

	@NotNull(message = "La catégorie ne peut pas être nulle")
	@ManyToOne
	private Categorie categorie;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Depense(int id, LocalDate date, double montant, Compte compte, String libelle, String description,
			Categorie categorie) {
		super(id, date, montant, compte);
		this.libelle = libelle;
		this.description = description;
		this.categorie = categorie;
	}

	public Depense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Depense(int id, LocalDate date, double montant, Compte compte) {
		super(id, date, montant, compte);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processTransaction() {
	}

}
