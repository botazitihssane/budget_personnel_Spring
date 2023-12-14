package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.budget.model.Depense;
import ma.emsi.budget.model.Epargne;
import ma.emsi.budget.model.Revenu;
import ma.emsi.budget.model.Transaction;
import ma.emsi.budget.repository.TransactionRepository;
import ma.emsi.budget.service.DepenseService;
import ma.emsi.budget.service.EpargneService;
import ma.emsi.budget.service.RevenuService;
import ma.emsi.budget.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private RevenuService revenuService;

	@Autowired
	private EpargneService epargneService;

	@Autowired
	private DepenseService depenseService;

	@Override
	public Transaction add(Transaction e) {
		return transactionRepository.save(e);
	}

	@Override
	public List<Transaction> getAll() {
		return transactionRepository.findAll();
	}

	@Override
	public Transaction getById(int id) {
		return transactionRepository.findTransactionById(id);
	}

	@Override
	public void update(Transaction e) {
		Transaction t = getById(e.getId());
		if (t != null) {
			t.setCompte(t.getCompte());
			t.setDate(e.getDate());
			t.setMontant(e.getMontant());
			transactionRepository.save(t);
		}
	}

	@Override
	public void delete(int id) {
		transactionRepository.deleteById(id);
	}

	@Override
	public void processTransaction(Transaction transaction) {
		if (transaction instanceof Revenu) {
			revenuService.processRevenu((Revenu) transaction);
		} else if (transaction instanceof Epargne) {
			epargneService.processEpargne((Epargne) transaction);
		} else if (transaction instanceof Depense) {
			depenseService.processDepense((Depense) transaction);
		}
	}

}
