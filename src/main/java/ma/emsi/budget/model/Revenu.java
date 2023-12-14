package ma.emsi.budget.model;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Revenu")
public class Revenu extends Transaction {
	@NotBlank(message = "La source de revenu ne peut pas être vide")
	private String sourceRevenu;
	private String categorie;

	public Revenu(int id, LocalDate date, double montant, Compte compte, String sourceRevenu, String categorie) {
		super(id, date, montant, compte);
		this.sourceRevenu = sourceRevenu;
		this.categorie = categorie;
	}

	public Revenu() {
		super();
	}

	public Revenu(int id, LocalDate date, double montant, Compte compte) {
		super(id, date, montant, compte);
	}

	public String getSourceRevenu() {
		return sourceRevenu;
	}

	public void setSourceRevenu(String sourceRevenu) {
		this.sourceRevenu = sourceRevenu;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public void processTransaction() {
		// TODO Auto-generated method stub
		
	}

}
