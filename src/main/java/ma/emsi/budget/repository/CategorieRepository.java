package ma.emsi.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.budget.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
	@Query("Select c from Categorie c where c.id= :id")
	Categorie findCategorieById(@Param("id") int id);

	@Query("Select c from Categorie c where c.nom = :nom")
	Categorie findCategorieByNom(@Param("nom") String nom);
	
	@Query("Select c from Categorie c where c.utilisateur.id = :id")
	List<Categorie> getByUser(@Param("id") int id);

}
