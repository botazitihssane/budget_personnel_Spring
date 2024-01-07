package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.BudgetCategorie;

public interface BudgetCategorieService {
	BudgetCategorie add(BudgetCategorie e);

	List<BudgetCategorie> getAll();

	BudgetCategorie getBudgetCategorieByCategorieAndUtilisateur(int categorie, int utilisateur);

	BudgetCategorie getById(int id);

	void update(BudgetCategorie e);

	void delete(int id);

	List<BudgetCategorie> getBudgetCategoriesForCurrentMonthAndUser(int utilisateur);

}
