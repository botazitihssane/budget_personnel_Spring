package ma.emsi.budget.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Compte;
import ma.emsi.budget.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query("SELECT t FROM Transaction t WHERE t.id = :id")
	Transaction findTransactionById(@Param("id") int id);

	@Query("SELECT t FROM Transaction t WHERE t.date >= :date")
	List<Transaction> findTransactionsByDate(@Param("date") LocalDate date);

	@Query("SELECT t FROM Transaction t WHERE t.montant > :montant")
	List<Transaction> findTransactionsByMontantSuperieurA(@Param("montant") double montant);

	@Query("SELECT t FROM Transaction t WHERE t.compte = :compte")
	List<Transaction> findTransactionsByCompte(@Param("compte") Compte compte);

	@Query("SELECT t FROM Transaction t WHERE t.compte.utilisateur.id = :id")
	List<Transaction> findTransactionsByUser(@Param("id") int id);
}
