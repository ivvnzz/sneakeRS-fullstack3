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

import group.sneakers.sneakers.service.product.ColorService;
import group.sneakers.sneakers.model.products.Color;

@RestController
@RequestMapping("/api/colors")
public class ControllerColor {

	@Autowired
	private ColorService colorService;

	@GetMapping
	public ResponseEntity<List<Color>> getAll() {
		List<Color> list = colorService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Color> getById(@PathVariable Long id) {
		Color color = colorService.findById(id);
		if (color == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(color);
	}

	@PostMapping
	public ResponseEntity<Color> create(@RequestBody Color color) {
		Color created = colorService.save(color);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Color> update(@PathVariable Long id, @RequestBody Color color) {
		color.setId(id);
		Color updated = colorService.save(color);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Color> partial(@PathVariable Long id, @RequestBody Color color) {
		color.setId(id);
		Color updated = colorService.partialUpdate(color);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		colorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
