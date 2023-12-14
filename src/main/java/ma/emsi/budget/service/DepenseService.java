package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Depense;

public interface DepenseService {
	Depense add(Depense e);

	List<Depense> getAll();

	Depense getById(int id);

	void update(Depense e);

	void delete(int id);

	void processDepense(Depense depense);

}
