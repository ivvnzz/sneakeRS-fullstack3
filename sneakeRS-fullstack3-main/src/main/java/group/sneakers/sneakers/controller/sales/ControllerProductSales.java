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

import group.sneakers.sneakers.service.sales.ProductSalesService;
import group.sneakers.sneakers.model.sales.ProductSales;

@RestController
@RequestMapping("/api/product-sales")
public class ControllerProductSales {

	@Autowired
	private ProductSalesService productSalesService;

	@GetMapping
	public ResponseEntity<List<ProductSales>> getAll() {
		List<ProductSales> list = productSalesService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductSales> getById(@PathVariable Integer id) {
		ProductSales productSales = productSalesService.findById(id);
		if (productSales == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productSales);
	}

	@PostMapping
	public ResponseEntity<ProductSales> create(@RequestBody ProductSales productSales) {
		ProductSales created = productSalesService.save(productSales);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductSales> update(@PathVariable Integer id, @RequestBody ProductSales productSales) {
		productSales.setId(id);
		ProductSales updated = productSalesService.save(productSales);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductSales> partial(@PathVariable Integer id, @RequestBody ProductSales productSales) {
		productSales.setId(id);
		ProductSales updated = productSalesService.partialUpdate(productSales);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		productSalesService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
