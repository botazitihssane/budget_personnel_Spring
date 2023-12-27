package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Compte;

public interface CompteService {
	Compte add(Compte e);

	List<Compte> getAll();

	Compte getById(int id);

	void update(Compte e);

	void delete(int id);

	List<Compte> findComptesByUser(int id);
}
