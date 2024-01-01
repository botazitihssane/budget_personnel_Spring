package ma.emsi.budget.controller;

import java.time.LocalDate;
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

import ma.emsi.budget.model.Transaction;
import ma.emsi.budget.service.TransactionService;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/transaction", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction u) {
		Transaction result = transactionService.add(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping(value = "/transactions", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Transaction>> getAllUsers() {
		List<Transaction> result = transactionService.getAll();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/transaction/user/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Transaction>> getAllByUser(@PathVariable int id) {
		List<Transaction> result = transactionService.findTransactionsByUser(id);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/transaction/date/{date}", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Transaction>> getByDate(@PathVariable LocalDate date) {
		List<Transaction> result = transactionService.findTransactionsByDate(date);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/transaction/montant/{montant}", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Transaction>> getByMontant(@PathVariable double montant) {
		List<Transaction> result = transactionService.findTransactionsByMontantSuperieurA(montant);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/transaction/id/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Transaction> getUser(@PathVariable int id) {
		Transaction result = transactionService.getById(id);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping(value = "/transaction/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		transactionService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/transaction", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> updateTransaction(@RequestBody Transaction u) {
		transactionService.update(u);
		return ResponseEntity.noContent().build();
	}
}
