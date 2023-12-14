package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.Categorie;
import ma.emsi.budget.repository.CategorieRepository;
import ma.emsi.budget.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public Categorie add(Categorie e) {
		return categorieRepository.save(e);
	}

	@Override
	public List<Categorie> getAll() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getById(int id) {
		return categorieRepository.findCategorieById(id);
	}

	@Override
	public void update(Categorie e) {
		Categorie o = getById(e.getId());
		if (o != null) {
			o.setNom(e.getNom());
			categorieRepository.save(o);
		}
	}

	@Override
	public void delete(int id) {
		categorieRepository.deleteById(id);
	}

}
