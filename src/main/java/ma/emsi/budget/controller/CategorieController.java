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

import ma.emsi.budget.model.Categorie;
import ma.emsi.budget.service.CategorieService;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*")
public class CategorieController {
	@Autowired
	private CategorieService categorieService;

	@PostMapping(value = "/categorie", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie u) {
		Categorie result = categorieService.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping(value = "/categories", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Categorie>> getAllUsers() {
		List<Categorie> result = categorieService.getAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/categorie/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Categorie> getUser(@PathVariable int id) {
		Categorie result = categorieService.getById(id);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/categorie/user/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Categorie>> getCategoriesByUser(@PathVariable int id) {
		List<Categorie> result = categorieService.getByUser(id);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/categorie/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		categorieService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/categorie", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateCategorie(@RequestBody Categorie u) {
		categorieService.update(u);
		return ResponseEntity.noContent().build();
	}
}
