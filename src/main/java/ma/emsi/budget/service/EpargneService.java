package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Epargne;

public interface EpargneService {
	Epargne add(Epargne e);

	List<Epargne> getAll();
	
	List<Epargne> getByUser(int id);

	Epargne getById(int id);

	void update(Epargne e);

	void delete(int id);

	void processEpargne(Epargne epargne);

}
