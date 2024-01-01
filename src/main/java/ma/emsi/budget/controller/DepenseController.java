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

import ma.emsi.budget.model.Depense;
import ma.emsi.budget.service.DepenseService;

@RestController
@RequestMapping("budget")
@CrossOrigin(origins = "*")
public class DepenseController {

	@Autowired
	private DepenseService depenseService;

	@PostMapping(value = "/depense", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Depense> addDepense(@RequestBody Depense depense) {
		Depense createdDepense = depenseService.add(depense);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDepense);
	}

	@GetMapping(value = "/depenses", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Depense>> getAllDepenses() {
		List<Depense> depenses = depenseService.getAll();
		return ResponseEntity.ok().body(depenses);
	}
	
	@GetMapping(value = "/depenses", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Depense>> getAllDepensesByUser() {
		List<Depense> depenses = depenseService.getAll();
		return ResponseEntity.ok().body(depenses);
	}

	@GetMapping(value = "/depense/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Depense> getDepenseById(@PathVariable int id) {
		Depense depense = depenseService.getById(id);
		return ResponseEntity.ok().body(depense);
	}

	@PutMapping(value = "/depense", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateDepense(@RequestBody Depense depense) {
		depenseService.update(depense);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/depense/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteDepense(@PathVariable int id) {
		depenseService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
