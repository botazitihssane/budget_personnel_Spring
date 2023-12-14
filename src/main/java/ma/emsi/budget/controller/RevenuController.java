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

import ma.emsi.budget.model.Revenu;
import ma.emsi.budget.service.RevenuService;

@RestController
@RequestMapping("budget")
@CrossOrigin(origins = "*")
public class RevenuController {

	@Autowired
	private RevenuService revenuService;

	@PostMapping(value = "/revenu", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Revenu> addRevenu(@RequestBody Revenu revenu) {
		Revenu createdRevenu = revenuService.add(revenu);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRevenu);
	}

	@GetMapping(value = "/revenus", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Revenu>> getAllRevenus() {
		List<Revenu> revenus = revenuService.getAll();
		return ResponseEntity.ok().body(revenus);
	}

	@GetMapping(value = "/revenu/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Revenu> getRevenuById(@PathVariable int id) {
		Revenu revenu = revenuService.getById(id);
		return ResponseEntity.ok().body(revenu);
	}

	@PutMapping(value = "/revenu", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateRevenu(@RequestBody Revenu revenu) {
		revenuService.update(revenu);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/revenu/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteRevenu(@PathVariable int id) {
		revenuService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
