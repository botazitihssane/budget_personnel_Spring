package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.Budget;
import ma.emsi.budget.repository.BudgetRepository;
import ma.emsi.budget.service.BudgetService;

@Service
public class BudgetServiceImpl implements BudgetService {
	@Autowired
	private BudgetRepository budgetRepository;

	@Override
	public Budget add(Budget e) {
		return budgetRepository.save(e);
	}

	@Override
	public List<Budget> getAll() {
		return budgetRepository.findAll();
	}

	@Override
	public Budget getById(int id) {
		return budgetRepository.findById(id);
	}

	@Override
	public void update(Budget e) {
		Budget o = getById(e.getId());
		if (o != null) {
			o.setAnnee(e.getAnnee());
			o.setMois(e.getMois());
			o.setUtilisateur(e.getUtilisateur());
			budgetRepository.save(o);
		}
	}

	@Override
	public void delete(int id) {
		budgetRepository.deleteById(id);
	}

}
