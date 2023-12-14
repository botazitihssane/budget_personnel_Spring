package ma.emsi.budget.service;

import java.util.List;
import ma.emsi.budget.model.Utilisateur;

public interface UtilisateurService {
	Utilisateur add(Utilisateur e);

	List<Utilisateur> getAll();

	Utilisateur getById(int id);

	void update(Utilisateur e);

	void delete(int id);

	void processOAuthPostLogin(String email);

}
