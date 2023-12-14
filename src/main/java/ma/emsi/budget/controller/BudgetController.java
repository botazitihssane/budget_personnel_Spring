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

import ma.emsi.budget.model.Budget;
import ma.emsi.budget.service.BudgetService;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*")
public class BudgetController {
	@Autowired
	private BudgetService budgetService;

	@PostMapping(value = "/budget", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Budget> createBudget(@RequestBody Budget u) {
		Budget result = budgetService.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping(value = "/budgets", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Budget>> getAllUsers() {
		List<Budget> result = budgetService.getAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/budget/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Budget> getUser(@PathVariable int id) {
		Budget result = budgetService.getById(id);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/budget/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		budgetService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/budget", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateBudget(@RequestBody Budget u) {
		budgetService.update(u);
		return ResponseEntity.noContent().build();
	}
}
