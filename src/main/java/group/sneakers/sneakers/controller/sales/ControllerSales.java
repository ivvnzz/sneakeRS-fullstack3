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

import group.sneakers.sneakers.service.sales.SalesService;
import group.sneakers.sneakers.model.sales.Sales;

@RestController
@RequestMapping("/api/sales")
public class ControllerSales {

	@Autowired
	private SalesService salesService;

	@GetMapping
	public ResponseEntity<List<Sales>> getAll() {
		List<Sales> list = salesService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sales> getById(@PathVariable Integer id) {
		Sales sales = salesService.findById(id);
		if (sales == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(sales);
	}

	@PostMapping
	public ResponseEntity<Sales> create(@RequestBody Sales sales) {
		Sales created = salesService.save(sales);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Sales> update(@PathVariable Integer id, @RequestBody Sales sales) {
		sales.setId(id);
		Sales updated = salesService.save(sales);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Sales> partial(@PathVariable Integer id, @RequestBody Sales sales) {
		sales.setId(id);
		Sales updated = salesService.partialUpdate(sales);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		salesService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
