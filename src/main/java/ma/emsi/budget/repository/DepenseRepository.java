package ma.emsi.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Depense;
@Repository
public interface DepenseRepository extends JpaRepository<Depense, Integer> {

	@Query("SELECT d FROM Depense d WHERE d.libelle = :libelle")
	List<Depense> findDepensesByLibelle(@Param("libelle") String libelle);

	@Query("SELECT d FROM Depense d WHERE d.categorie.id = :categorie")
	List<Depense> findDepensesByCategorie(@Param("categorie") int categorie);

	@Query("SELECT d FROM Depense d WHERE d.id = :id")
	Depense findDepenseById(@Param("id") int id);
	
	@Query("Select d from Depense d where d.compte.utilisateur = :id")
	List<Depense> findDepenseByUser(@Param("id") int id);

	@Query("SELECT d FROM Depense d WHERE MONTH(d.date) = MONTH(CURRENT_DATE) AND YEAR(d.date) = YEAR(CURRENT_DATE) AND d.compte.utilisateur.id = :userId")
	List<Depense> findDepensesByCurrentMonthAndUser(@Param("userId") int userId);

}
