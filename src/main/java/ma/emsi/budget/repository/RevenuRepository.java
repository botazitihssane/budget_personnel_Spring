package ma.emsi.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Revenu;

@Repository
public interface RevenuRepository extends JpaRepository<Revenu, Integer> {
	@Query("SELECT r FROM Revenu r WHERE r.sourceRevenu = :sourceRevenu")
	List<Revenu> findRevenusBySource(@Param("sourceRevenu") String sourceRevenu);

	@Query("SELECT r FROM Revenu r WHERE r.categorie = :categorie")
	List<Revenu> findRevenusByCategorie(@Param("categorie") String categorie);

	@Query("Select r from Revenu r where r.id = :id")
	Revenu finById(@Param("id") int id);

	@Query("SELECT r FROM Revenu r WHERE r.compte.utilisateur.id = :userId")
    List<Revenu> findRevenusByUser(@Param("userId") int userId);
}
