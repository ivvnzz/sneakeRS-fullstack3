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

import group.sneakers.sneakers.service.product.MaterialService;
import group.sneakers.sneakers.model.products.Material;

@RestController
@RequestMapping("/api/materials")
public class ControllerMaterial {

	@Autowired
	private MaterialService materialService;

	@GetMapping
	public ResponseEntity<List<Material>> getAll() {
		List<Material> list = materialService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Material> getById(@PathVariable Integer id) {
		Material material = materialService.findById(id);
		if (material == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(material);
	}

	@PostMapping
	public ResponseEntity<Material> create(@RequestBody Material material) {
		Material created = materialService.save(material);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Material> update(@PathVariable Integer id, @RequestBody Material material) {
		material.setId(id);
		Material updated = materialService.save(material);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Material> partial(@PathVariable Integer id, @RequestBody Material material) {
		material.setId(id);
		Material updated = materialService.partialUpdate(material);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		materialService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
