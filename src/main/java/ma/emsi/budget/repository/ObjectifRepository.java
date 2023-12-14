package ma.emsi.budget.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Objectif;

@Repository
public interface ObjectifRepository extends JpaRepository<Objectif, Integer> {
	@Query("SELECT o FROM Objectif o WHERE o.titre = :titre")
	List<Objectif> findObjectifsByTitre(@Param("titre") String titre);

	@Query("SELECT o FROM Objectif o WHERE o.dateLimite = :dateLimite")
	List<Objectif> findObjectifsByDateLimite(@Param("dateLimite") LocalDate dateLimite);

	@Query("SELECT o FROM Objectif o WHERE o.utilisateur.id = :id")
	List<Objectif> findObjectifsByUtilisateur(@Param("id") int id);

	@Query("Select o from Objectif o where o.id = :id")
	Objectif findById(@Param("id") int id);
}
