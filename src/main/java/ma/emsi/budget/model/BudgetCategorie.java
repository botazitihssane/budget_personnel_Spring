package ma.emsi.budget.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "BudgetCategorie")
public class BudgetCategorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Categorie categorie;
	@ManyToOne
	private Budget budget;
    @Positive(message = "Le montant mensuel doit être positif")
	private double montantMensuel;
    private double montantDepense;

	public BudgetCategorie(int id, Categorie categorie, Budget budget, double montantMensuel, double montantDepense) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.budget = budget;
		this.montantMensuel = montantMensuel;
		this.montantDepense = montantDepense;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public double getMontantMensuel() {
		return montantMensuel;
	}

	public void setMontantMensuel(double montantMensuel) {
		this.montantMensuel = montantMensuel;
	}

	public double getMontantDepense() {
		return montantDepense;
	}

	public void setMontantDepense(double montantDepense) {
		this.montantDepense = montantDepense;
	}

	public BudgetCategorie() {
		super();
	}

}
