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

import group.sneakers.sneakers.service.product.SizeService;
import group.sneakers.sneakers.model.products.Size;

@RestController
@RequestMapping("/api/sizes")
public class ControllerSize {

	@Autowired
	private SizeService sizeService;

	@GetMapping
	public ResponseEntity<List<Size>> getAll() {
		List<Size> list = sizeService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Size> getById(@PathVariable Integer id) {
		Size size = sizeService.findById(id);
		if (size == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(size);
	}

	@PostMapping
	public ResponseEntity<Size> create(@RequestBody Size size) {
		Size created = sizeService.save(size);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Size> update(@PathVariable Integer id, @RequestBody Size size) {
		size.setId(id);
		Size updated = sizeService.save(size);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Size> partial(@PathVariable Integer id, @RequestBody Size size) {
		size.setId(id);
		Size updated = sizeService.partialUpdate(size);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		sizeService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
