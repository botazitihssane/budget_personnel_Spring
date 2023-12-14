package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.BudgetCategorie;
import ma.emsi.budget.repository.BudgetCategorieRepository;
import ma.emsi.budget.service.BudgetCategorieService;

@Service
public class BudgetCategorieServiceImpl implements BudgetCategorieService {
	@Autowired
	private BudgetCategorieRepository budgetCategorieRepository;

	@Override
	public BudgetCategorie add(BudgetCategorie e) {
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
			System.out.println("in budgte"+ e.getMontantDepense()+"\n");

			budgetCategorieRepository.save(o);
			System.out.println("after budgte"+ o.getMontantDepense()+"\n");
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

}
