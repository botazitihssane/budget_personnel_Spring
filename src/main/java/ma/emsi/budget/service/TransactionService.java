package ma.emsi.budget.service;

import java.util.List;

import ma.emsi.budget.model.Transaction;

public interface TransactionService {
	Transaction add(Transaction e);

	List<Transaction> getAll();

	Transaction getById(int id);

	void update(Transaction e);

	void delete(int id);
	
    void processTransaction(Transaction transaction);

}
