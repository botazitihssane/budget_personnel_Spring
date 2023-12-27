package ma.emsi.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Compte;
@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
	@Query("SELECT c FROM Compte c WHERE c.id = :id")
	Compte findCompteById(@Param("id") int id);

	@Query("SELECT c FROM Compte c WHERE c.utilisateur.id = :userId")
	List<Compte> findComptesByUser(@Param("userId") int userId);
}
