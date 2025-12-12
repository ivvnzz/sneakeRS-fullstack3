package group.sneakers.sneakers.controller.products;

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

import group.sneakers.sneakers.service.product.ProductService;
import group.sneakers.sneakers.model.products.Product;

@RestController
@RequestMapping("/api/products")
public class ControllerProduct {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> list = productService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Integer id) {
		Product product = productService.findById(id);
		if (product == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(product);
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product created = productService.save(product);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
		product.setId(id);
		Product updated = productService.save(product);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Product> partial(@PathVariable Integer id, @RequestBody Product product) {
		product.setId(id);
		Product updated = productService.partialUpdate(product);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		productService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
