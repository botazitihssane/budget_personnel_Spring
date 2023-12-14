package ma.emsi.budget.repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Budget;
import ma.emsi.budget.model.Utilisateur;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
	@Query("Select b from Budget b where b.utilisateur.id = :id")
	List<Budget> findByUtilisateur(@Param("id") int id);

	@Query("SELECT b FROM Budget b WHERE b.utilisateur = :utilisateur AND b.mois = :mois AND b.annee = :annee")
	List<Budget> findByUtilisateurAndMoisAndAnnee(@Param("utilisateur") Utilisateur utilisateur,
			@Param("mois") Month mois, @Param("annee") Year annee);

	@Query("Select b from Budget b where b.id = :id")
	Budget findById(@Param("id") int id);

}
