package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.Objectif;
import ma.emsi.budget.repository.ObjectifRepository;
import ma.emsi.budget.service.ObjectifService;

@Service
public class ObjectifServiceImpl implements ObjectifService {

	@Autowired
	private ObjectifRepository objectifRepository;

	@Override
	public Objectif add(Objectif e) {
		return objectifRepository.save(e);
	}

	@Override
	public List<Objectif> getAll() {
		return objectifRepository.findAll();
	}

	@Override
	public Objectif getById(int id) {
		return objectifRepository.findById(id);
	}

	@Override
	public void update(Objectif e) {
		Objectif o = getById(e.getId());
		if (o != null) {
			o.setDateLimite(e.getDateLimite());
			o.setDescription(e.getDescription());
			o.setMontantActuel(e.getMontantActuel());
			o.setMontantCible(e.getMontantCible());
			o.setTitre(e.getTitre());
			o.setUtilisateur(e.getUtilisateur());
			objectifRepository.save(o);
		}
	}

	@Override
	public void delete(int id) {
		objectifRepository.deleteById(id);
	}

	@Override
	public List<Objectif> getByUSER(int id) {
		return objectifRepository.findObjectifsByUtilisateur(id);
	}

}
