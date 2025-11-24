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

import group.sneakers.sneakers.service.product.CategoryService;
import group.sneakers.sneakers.model.products.Category;

@RestController
@RequestMapping("/api/categories")
public class ControllerCategory {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		List<Category> list = categoryService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Integer id) {
		Category category = categoryService.findById(id);
		if (category == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(category);
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category) {
		Category created = categoryService.save(category);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
		category.setId(id);
		Category updated = categoryService.save(category);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Category> partial(@PathVariable Integer id, @RequestBody Category category) {
		category.setId(id);
		Category updated = categoryService.partialUpdate(category);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
