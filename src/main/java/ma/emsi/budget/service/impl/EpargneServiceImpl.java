package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.Compte;
import ma.emsi.budget.model.Epargne;
import ma.emsi.budget.model.Objectif;
import ma.emsi.budget.repository.EpargneRepository;
import ma.emsi.budget.repository.ObjectifRepository;
import ma.emsi.budget.service.CompteService;
import ma.emsi.budget.service.EpargneService;

@Service
public class EpargneServiceImpl implements EpargneService {
	@Autowired
	private EpargneRepository epargneRepository;

	@Autowired
	private CompteService compteService;

	@Autowired
	private ObjectifRepository objectifRepository;

	@Override
	public Epargne add(Epargne epargne) {
		processEpargne(epargne);
		return epargneRepository.save(epargne);
	}

	@Override
	public List<Epargne> getAll() {
		return epargneRepository.findAll();
	}

	@Override
	public Epargne getById(int id) {
		return epargneRepository.findEpargneById(id);
	}

	@Override
	public void update(Epargne updatedEpargne) {
		Epargne existingEpargne = getById(updatedEpargne.getId());
		if (existingEpargne != null) {
			updateEpargne(existingEpargne, updatedEpargne);
			existingEpargne.setCompte(updatedEpargne.getCompte());
			existingEpargne.setDate(updatedEpargne.getDate());
			existingEpargne.setMontant(updatedEpargne.getMontant());
			existingEpargne.setObjectif(updatedEpargne.getObjectif());
			epargneRepository.save(existingEpargne);
		}
	}

	@Override
	public void delete(int id) {
		Epargne deletedEpargne = epargneRepository.findById(id).orElse(null);
		if (deletedEpargne != null) {
			undoProcessEpargne(deletedEpargne);
			epargneRepository.deleteById(id);
		}
	}

	private void updateEpargne(Epargne existingEpargne, Epargne updatedEpargne) {
		undoProcessEpargne(existingEpargne);
		epargneRepository.save(updatedEpargne);
		processEpargne(updatedEpargne);
	}

	@Override
	public void processEpargne(Epargne epargne) {
		updateEntitiesAfterEpargneUpdate(epargne, true);
	}

	private void undoProcessEpargne(Epargne epargne) {
		updateEntitiesAfterEpargneUpdate(epargne, false);
	}

	private void updateEntitiesAfterEpargneUpdate(Epargne epargne, boolean isAddition) {
		Objectif objectif = epargne.getObjectif();

		// Update montantActuel in the Objectif entity
		double montantActuelObjectif = objectif.getMontantActuel()
				+ (isAddition ? epargne.getMontant() : -epargne.getMontant());
		objectif.setMontantActuel(montantActuelObjectif);
		objectifRepository.save(objectif);

		// Update solde in the Compte entity
		Compte compte = epargne.getCompte();
		double soldeDifference = isAddition ? -epargne.getMontant() : epargne.getMontant();
		double nouveauSoldeCompte = compte.getSolde() + soldeDifference;
		compte.setSolde(nouveauSoldeCompte);
		compteService.update(compte);
	}

	@Override
	public List<Epargne> getByUser(int id) {
		return epargneRepository.findByUser(id);
	}
}
