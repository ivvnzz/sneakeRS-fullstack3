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

import group.sneakers.sneakers.service.product.OriginService;
import group.sneakers.sneakers.model.products.Origin;

@RestController
@RequestMapping("/api/origins")
public class ControllerOrigin {

	@Autowired
	private OriginService originService;

	@GetMapping
	public ResponseEntity<List<Origin>> getAll() {
		List<Origin> list = originService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Origin> getById(@PathVariable Integer id) {
		Origin origin = originService.findById(id);
		if (origin == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(origin);
	}

	@PostMapping
	public ResponseEntity<Origin> create(@RequestBody Origin origin) {
		Origin created = originService.save(origin);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Origin> update(@PathVariable Integer id, @RequestBody Origin origin) {
		origin.setId(id);
		Origin updated = originService.save(origin);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Origin> partial(@PathVariable Integer id, @RequestBody Origin origin) {
		origin.setId(id);
		Origin updated = originService.partialUpdate(origin);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		originService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
