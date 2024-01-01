package ma.emsi.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Epargne;

@Repository
public interface EpargneRepository extends JpaRepository<Epargne, Integer> {
	@Query("SELECT e FROM Epargne e WHERE e.objectif.id = :id")
	List<Epargne> findEpargneByObjectif(@Param("id") int id);

	@Query("SELECT e FROM Epargne e WHERE e.id = :id")
	Epargne findEpargneById(@Param("id") int id);

	@Query("SELECT e FROM Epargne e WHERE e.objectif.utilisateur.id = :id")
	List<Epargne> findByUser(@Param("id") int id);;
}
