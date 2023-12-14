package ma.emsi.budget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.budget.model.Utilisateur;
import ma.emsi.budget.service.UtilisateurService;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*")
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;

	@PostMapping(value = "/utilisateur", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur u) {
		Utilisateur result = utilisateurService.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping(value = "/utilisateur/all", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Utilisateur>> getAllUsers() {
		List<Utilisateur> result = utilisateurService.getAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/utilisateur/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Utilisateur> getUser(@PathVariable int id) {
		Utilisateur result = utilisateurService.getById(id);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/utilisateur/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		utilisateurService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/utilisateur", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateUtilisateur(@RequestBody Utilisateur u) {
		utilisateurService.update(u);
		return ResponseEntity.noContent().build();
	}
}
