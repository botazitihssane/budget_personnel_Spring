package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.BudgetCategorie;
import ma.emsi.budget.model.Compte;
import ma.emsi.budget.model.Depense;
import ma.emsi.budget.repository.DepenseRepository;
import ma.emsi.budget.service.BudgetCategorieService;
import ma.emsi.budget.service.CompteService;
import ma.emsi.budget.service.DepenseService;

@Service
public class DepenseServiceImpl implements DepenseService {
	@Autowired
	private DepenseRepository depenseRepository;

	@Autowired
	private CompteService compteService;

	@Autowired
	private BudgetCategorieService budgetCategorieService;

	@Override
	public Depense add(Depense e) {
		Depense newDepense = depenseRepository.save(e);
		processDepense(newDepense);
		return newDepense;
	}

	@Override
	public List<Depense> getAll() {
		return depenseRepository.findAll();
	}

	@Override
	public Depense getById(int id) {
		return depenseRepository.findDepenseById(id);
	}

	@Override
	public void update(Depense updatedDepense) {
		Depense existingDepense = getById(updatedDepense.getId());
		if (updatedDepense != null) {
			updateCompteAfterDepenseUpdate(existingDepense, updatedDepense);
			existingDepense.setCompte(updatedDepense.getCompte());
			existingDepense.setDate(updatedDepense.getDate());
			existingDepense.setMontant(updatedDepense.getMontant());
			existingDepense.setCategorie(updatedDepense.getCategorie());
			existingDepense.setDescription(updatedDepense.getDescription());
			existingDepense.setLibelle(updatedDepense.getLibelle());
			depenseRepository.save(existingDepense);

		}
	}

	@Override
	public void delete(int id) {
		Depense deletedDepense = depenseRepository.findById(id).orElse(null);
		if (deletedDepense != null) {
			undoProcessDepense(deletedDepense);
			depenseRepository.deleteById(id);
		}
	}

	private void undoProcessDepense(Depense depense) {
		updateCompteAfterDepenseUpdate(depense, false);
	}

	private void updateCompteAfterDepenseUpdate(Depense existingDepense, boolean isDeletion) {
		Compte compte = existingDepense.getCompte();
		double depenseMontant = existingDepense.getMontant();
		int categorieId = existingDepense.getCategorie().getId();
		double amountDifference = isDeletion ? -depenseMontant : depenseMontant;
		double nouveauSolde = compte.getSolde() + amountDifference;
		compte.setSolde(nouveauSolde);
		compteService.update(compte);
		updateBudgetCategorie(categorieId, amountDifference, false, compte);
	}

	@Override
	public void processDepense(Depense depense) {
		Compte compte = depense.getCompte();
		double nouveauSolde = compte.getSolde() - depense.getMontant();
		compte.setSolde(nouveauSolde);
		compteService.update(compte);

		int categorie = depense.getCategorie().getId();
		BudgetCategorie budgetCategorie = budgetCategorieService.getBudgetCategorieByCategorieAndUtilisateur(categorie,
				compte.getUtilisateur().getId());

		if (budgetCategorie != null) {
			double nouveauMontantDepense = budgetCategorie.getMontantDepense() + depense.getMontant();

			budgetCategorie.setMontantDepense(nouveauMontantDepense);
			budgetCategorieService.update(budgetCategorie);
		}
	}

	private void updateCompteAfterDepenseUpdate(Depense existingDepense, Depense updatedDepense) {
		// Updating the compte
		Compte compte = existingDepense.getCompte();
		double amountDifference = updatedDepense.getMontant() - existingDepense.getMontant();

		if (amountDifference != 0) {
			double nouveauSolde = compte.getSolde() - amountDifference;
			compte.setSolde(nouveauSolde);
			compteService.update(compte);
		}
		System.out.println(amountDifference);
		updateBudgetCategorie(existingDepense.getCategorie().getId(), amountDifference, true, compte);

	}

	private void updateBudgetCategorie(int categorieId, double amount, boolean isAddition, Compte compte) {
		BudgetCategorie budgetCategorie = budgetCategorieService
				.getBudgetCategorieByCategorieAndUtilisateur(categorieId, compte.getUtilisateur().getId());
		System.out.println("amout" + amount + "\n");
		if (budgetCategorie != null) {
			double newMontantDepense = isAddition ? budgetCategorie.getMontantDepense() + amount
					: budgetCategorie.getMontantDepense() - amount;
			System.out.println("newMontantDepense" + newMontantDepense + "\n");
			budgetCategorie.setMontantDepense(newMontantDepense);
			budgetCategorieService.update(budgetCategorie);
		}
	}

	@Override
	public List<Depense> findByUser(int id) {
		return depenseRepository.findDepenseByUser(id);
	}

	@Override
	public List<Depense> findByCurrentMonthAndUser(int id) {
	    return depenseRepository.findDepensesByCurrentMonthAndUser(id);
	}

}
