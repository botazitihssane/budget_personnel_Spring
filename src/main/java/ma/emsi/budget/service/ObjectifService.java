package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Objectif;

public interface ObjectifService {
	Objectif add(Objectif e);

	List<Objectif> getAll();
	
	List<Objectif> getByUSER(int id);

	Objectif getById(int id);

	void update(Objectif e);

	void delete(int id);
}
