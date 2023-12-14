package ma.emsi.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.BudgetCategorie;

@Repository
public interface BudgetCategorieRepository extends JpaRepository<BudgetCategorie, Integer> {

	@Query("SELECT bc FROM BudgetCategorie bc WHERE bc.categorie.id = :categorie")
	List<BudgetCategorie> findByCategorie(@Param("categorie") int categorie);

	@Query("SELECT bc FROM BudgetCategorie bc WHERE bc.budget.id = :budget")
	List<BudgetCategorie> findByBudget(@Param("budget") int budget);

	@Query("SELECT bc from BudgetCategorie bc where bc.id = :id")
	BudgetCategorie findById(@Param("id") int id);

	@Query("SELECT bc FROM BudgetCategorie bc WHERE bc.categorie.id = :categorie AND bc.budget.utilisateur.id = :utilisateur")
	BudgetCategorie findByCategorieAndUtilisateur(@Param("categorie") int categorie,
			@Param("utilisateur") int utilisateur);

}