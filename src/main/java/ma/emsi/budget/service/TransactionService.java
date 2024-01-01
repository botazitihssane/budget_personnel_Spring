package ma.emsi.budget.service;

import java.time.LocalDate;
import java.util.List;

import ma.emsi.budget.model.Compte;
import ma.emsi.budget.model.Transaction;

public interface TransactionService {
	Transaction add(Transaction e);

	List<Transaction> getAll();

	Transaction getById(int id);

	void update(Transaction e);

	void delete(int id);
	
    void processTransaction(Transaction transaction);

    List<Transaction> findTransactionsByDate(LocalDate date);

    List<Transaction> findTransactionsByMontantSuperieurA(double montant);

    List<Transaction> findTransactionsByCompte(Compte compte);

    List<Transaction> findTransactionsByUser(int id);
}
