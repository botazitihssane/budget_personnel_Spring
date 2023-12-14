package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Budget;

public interface BudgetService {
	Budget add(Budget e);

	List<Budget> getAll();

	Budget getById(int id);

	void update(Budget e);

	void delete(int id);
}
