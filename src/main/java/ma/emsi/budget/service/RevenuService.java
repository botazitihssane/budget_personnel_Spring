package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Revenu;

public interface RevenuService {
	Revenu add(Revenu e);

	List<Revenu> getAll();

	Revenu getById(int id);

	void update(Revenu e);

	void delete(int id);

	void processRevenu(Revenu revenu);

}
