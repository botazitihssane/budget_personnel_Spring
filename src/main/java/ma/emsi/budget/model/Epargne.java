package ma.emsi.budget.model;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Epargne")
public class Epargne extends Transaction {
	@ManyToOne
	private Objectif objectif;

	public Objectif getObjectif() {
		return objectif;
	}

	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
	}

	public Epargne(int id, LocalDate date, double montant, Compte compte, Objectif objectif) {
		super(id, date, montant, compte);
		this.objectif = objectif;
	}

	public Epargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Epargne(int id, LocalDate date, double montant, Compte compte) {
		super(id, date, montant, compte);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processTransaction() {
	}

}
