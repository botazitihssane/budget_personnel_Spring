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

import ma.emsi.budget.model.Objectif;
import ma.emsi.budget.service.ObjectifService;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*")
public class ObjectifController {
	@Autowired
	private ObjectifService objectifService;

	@PostMapping(value = "/objectif", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Objectif> createObjectif(@RequestBody Objectif u) {
		Objectif result = objectifService.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping(value = "/objectifs", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Objectif>> getAllUsers() {
		List<Objectif> result = objectifService.getAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/objectif/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Objectif> getUser(@PathVariable int id) {
		Objectif result = objectifService.getById(id);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/objectif/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		objectifService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/objectif", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateObjectif(@RequestBody Objectif u) {
		objectifService.update(u);
		return ResponseEntity.noContent().build();
	}
}

