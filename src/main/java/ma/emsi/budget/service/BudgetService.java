package ma.emsi.budget.service;

import java.time.Month;
import java.time.Year;
import java.util.List;

import ma.emsi.budget.model.Budget;
import ma.emsi.budget.model.Utilisateur;

public interface BudgetService {
	Budget add(Budget e);

	List<Budget> getAll();

	Budget getById(int id);

	void update(Budget e);

	void delete(int id);

	List<Budget> findByUtilisateur(int id);

	Budget findByUtilisateurAndMoisAndAnnee(Utilisateur utilisateur, Month mois, Year annee);

}
