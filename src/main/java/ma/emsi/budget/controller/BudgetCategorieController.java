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

import ma.emsi.budget.model.BudgetCategorie;
import ma.emsi.budget.service.BudgetCategorieService;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*")
public class BudgetCategorieController {
	@Autowired
	private BudgetCategorieService budgetCategorieService;

	@PostMapping(value = "/budgetCategorie", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<BudgetCategorie> createBudgetCategorie(@RequestBody BudgetCategorie u) {
		BudgetCategorie result = budgetCategorieService.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping(value = "/budgetCategories", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<BudgetCategorie>> getAllUsers() {
		List<BudgetCategorie> result = budgetCategorieService.getAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/budgetCategorie/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<BudgetCategorie> getUser(@PathVariable int id) {
		BudgetCategorie result = budgetCategorieService.getById(id);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/budgetCategories/currentMonth/{utilisateur}", produces = { "application/json",
			"application/xml" })
	public ResponseEntity<List<BudgetCategorie>> getBudgetCategoriesForCurrentMonthAndUser(
			@PathVariable int utilisateur) {
		List<BudgetCategorie> result = budgetCategorieService.getBudgetCategoriesForCurrentMonthAndUser(utilisateur);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/budgetCategorie/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		budgetCategorieService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/budgetCategorie", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateBudgetCategorie(@RequestBody BudgetCategorie u) {
		budgetCategorieService.update(u);
		return ResponseEntity.noContent().build();
	}
}
