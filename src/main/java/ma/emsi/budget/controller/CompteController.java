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

import ma.emsi.budget.model.Compte;
import ma.emsi.budget.service.CompteService;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*")
public class CompteController {
	@Autowired
	private CompteService compteService;

	@PostMapping(value = "/compte", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Compte> createCompte(@RequestBody Compte u) {
		Compte result = compteService.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping(value = "/comptes", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Compte>> getAllUsers() {
		List<Compte> result = compteService.getAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/compte/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Compte> getUser(@PathVariable int id) {
		Compte result = compteService.getById(id);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/compte/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		compteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/compte", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateCompte(@RequestBody Compte u) {
		compteService.update(u);
		return ResponseEntity.noContent().build();
	}
}
