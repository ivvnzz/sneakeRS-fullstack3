package group.sneakers.sneakers.controller.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.sneakers.sneakers.service.sales.PaymentMetodService;
import group.sneakers.sneakers.model.sales.PaymentMetod;

@RestController
@RequestMapping("/api/payment-methods")
public class ControllerPaymentMetod {

	@Autowired
	private PaymentMetodService paymentMetodService;

	@GetMapping
	public ResponseEntity<List<PaymentMetod>> getAll() {
		List<PaymentMetod> list = paymentMetodService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentMetod> getById(@PathVariable Integer id) {
		PaymentMetod paymentMetod = paymentMetodService.findById(id);
		if (paymentMetod == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(paymentMetod);
	}

	@PostMapping
	public ResponseEntity<PaymentMetod> create(@RequestBody PaymentMetod paymentMetod) {
		PaymentMetod created = paymentMetodService.save(paymentMetod);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PaymentMetod> update(@PathVariable Integer id, @RequestBody PaymentMetod paymentMetod) {
		paymentMetod.setId(id);
		PaymentMetod updated = paymentMetodService.save(paymentMetod);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PaymentMetod> partial(@PathVariable Integer id, @RequestBody PaymentMetod paymentMetod) {
		paymentMetod.setId(id);
		PaymentMetod updated = paymentMetodService.partialUpdate(paymentMetod);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		paymentMetodService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
