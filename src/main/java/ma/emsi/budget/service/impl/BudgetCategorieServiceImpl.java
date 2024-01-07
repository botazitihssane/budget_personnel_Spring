package ma.emsi.budget.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.Budget;
import ma.emsi.budget.model.BudgetCategorie;
import ma.emsi.budget.model.Utilisateur;
import ma.emsi.budget.repository.BudgetCategorieRepository;
import ma.emsi.budget.repository.BudgetRepository;
import ma.emsi.budget.service.BudgetCategorieService;

@Service
public class BudgetCategorieServiceImpl implements BudgetCategorieService {
	@Autowired
	private BudgetCategorieRepository budgetCategorieRepository;

	@Autowired
	private BudgetRepository budgetRepository;

	@Override
	public BudgetCategorie add(BudgetCategorie e) {
		Utilisateur utilisateur = e.getBudget().getUtilisateur();
		int budgetMonth = e.getBudget().getMois();
		int budgetYear = e.getBudget().getAnnee();
		Budget existingBudget = budgetRepository.findByUtilisateurAndMoisAndAnnee(utilisateur, budgetMonth, budgetYear);

		if (existingBudget != null) {
			e.setBudget(existingBudget);
		} else {
			Budget newBudget = new Budget();
			newBudget.setUtilisateur(utilisateur);
			newBudget.setMois(budgetMonth);
			newBudget.setAnnee(budgetYear);
			existingBudget = budgetRepository.save(newBudget);
			e.setBudget(existingBudget);
		}

		return budgetCategorieRepository.save(e);
	}

	@Override
	public List<BudgetCategorie> getAll() {
		return budgetCategorieRepository.findAll();
	}

	@Override
	public BudgetCategorie getById(int id) {
		return budgetCategorieRepository.findById(id);
	}

	@Override
	public void update(BudgetCategorie e) {
		BudgetCategorie o = getById(e.getId());
		if (o != null) {
			o.setCategorie(e.getCategorie());
			o.setBudget(e.getBudget());
			o.setMontantMensuel(e.getMontantMensuel());
			o.setMontantDepense(e.getMontantDepense());
			System.out.println("in budgte" + e.getMontantDepense() + "\n");

			budgetCategorieRepository.save(o);
			System.out.println("after budgte" + o.getMontantDepense() + "\n");
		}
	}

	@Override
	public void delete(int id) {
		budgetCategorieRepository.deleteById(id);
	}

	@Override
	public BudgetCategorie getBudgetCategorieByCategorieAndUtilisateur(int categorie, int utilisateur) {
		return budgetCategorieRepository.findByCategorieAndUtilisateur(categorie, utilisateur);
	}

	@Override
	public List<BudgetCategorie> getBudgetCategoriesForCurrentMonthAndUser(int utilisateur) {
		LocalDate currentDate = LocalDate.now();
		return budgetCategorieRepository.findBudgetCategoriesForCurrentMonthAndUser(utilisateur,
				currentDate.getMonthValue(), currentDate.getYear());
	}

}
