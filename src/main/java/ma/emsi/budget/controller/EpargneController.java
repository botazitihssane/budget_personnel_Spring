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

import ma.emsi.budget.model.Epargne;
import ma.emsi.budget.service.EpargneService;

@RestController
@RequestMapping("budget")
@CrossOrigin(origins = "*")
public class EpargneController {

	@Autowired
	private EpargneService epargneService;

	@PostMapping(value = "/epargne", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Epargne> addEpargne(@RequestBody Epargne epargne) {
		Epargne createdEpargne = epargneService.add(epargne);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEpargne);
	}

	@GetMapping(value = "/epargnes", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Epargne>> getAllEpargnes() {
		List<Epargne> epargnes = epargneService.getAll();
		return ResponseEntity.ok().body(epargnes);
	}

	@GetMapping(value = "/epargne/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Epargne> getEpargneById(@PathVariable int id) {
		Epargne epargne = epargneService.getById(id);
		return ResponseEntity.ok().body(epargne);
	}

	@GetMapping(value = "/epargne/user/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Epargne>> getEpargneByUserId(@PathVariable int id) {
		List<Epargne> epargne = epargneService.getByUser(id);
		return ResponseEntity.ok().body(epargne);
	}

	@PutMapping(value = "/epargne", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateEpargne(@RequestBody Epargne epargne) {
		epargneService.update(epargne);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/epargne/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteEpargne(@PathVariable int id) {
		epargneService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
