package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Categorie;

public interface CategorieService {
	Categorie add(Categorie e);

	List<Categorie> getAll();

	Categorie getById(int id);

	void update(Categorie e);

	void delete(int id);
}
