package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.Compte;
import ma.emsi.budget.model.Revenu;
import ma.emsi.budget.repository.RevenuRepository;
import ma.emsi.budget.service.CompteService;
import ma.emsi.budget.service.RevenuService;

@Service
public class RevenuServiceImpl implements RevenuService {

	@Autowired
	private RevenuRepository revenuRepository;

	@Autowired
	private CompteService compteService;

	@Override
	public Revenu add(Revenu e) {
		processRevenu(e);
		return revenuRepository.save(e);
	}

	@Override
	public List<Revenu> getAll() {
		return revenuRepository.findAll();
	}

	@Override
	public Revenu getById(int id) {
		return revenuRepository.finById(id);
	}

	@Override
	public void update(Revenu updatedRevenu) {
		Revenu existingRevenu = getById(updatedRevenu.getId());
		if (existingRevenu != null) {
			existingRevenu.setCategorie(updatedRevenu.getCategorie());
			existingRevenu.setDate(updatedRevenu.getDate());
			existingRevenu.setMontant(updatedRevenu.getMontant());
			existingRevenu.setSourceRevenu(updatedRevenu.getSourceRevenu());
			existingRevenu.setCompte(updatedRevenu.getCompte());
			processRevenu(existingRevenu);
			revenuRepository.save(existingRevenu);
		}
	}

	@Override
	public void delete(int id) {
		Revenu deletedRevenu = revenuRepository.findById(id).orElse(null);
		if (deletedRevenu != null) {
			undoProcessRevenu(deletedRevenu);
			revenuRepository.deleteById(id);
		}
	}

	private void undoProcessRevenu(Revenu revenu) {
		Compte compte = revenu.getCompte();
		double nouveauSoldeCompte = compte.getSolde() - revenu.getMontant();
		compte.setSolde(nouveauSoldeCompte);
		compteService.update(compte);
	}

	@Override
	public void processRevenu(Revenu revenu) {
		double soldeCourant = revenu.getCompte().getSolde();
		System.out.println("Solde courant " + soldeCourant + "\n");
		double nouveauSolde = soldeCourant + revenu.getMontant();
		revenu.getCompte().setSolde(nouveauSolde);
		System.out.println("Solde nouveau " + nouveauSolde);
		compteService.update(revenu.getCompte());
		revenuRepository.save(revenu);

	}

}
