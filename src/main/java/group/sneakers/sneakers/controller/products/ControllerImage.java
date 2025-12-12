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

import group.sneakers.sneakers.service.product.ImageService;
import group.sneakers.sneakers.model.products.Image;

@RestController
@RequestMapping("/api/images")
public class ControllerImage {

	@Autowired
	private ImageService imageService;

	@GetMapping
	public ResponseEntity<List<Image>> getAll() {
		List<Image> list = imageService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Image> getById(@PathVariable Integer id) {
		Image image = imageService.findById(id);
		if (image == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(image);
	}

	@PostMapping
	public ResponseEntity<Image> create(@RequestBody Image image){
		Image created = imageService.save(image);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Image> update(@PathVariable Integer id, @RequestBody Image image) {
		image.setId(id);
		Image updated = imageService.save(image);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Image> partial(@PathVariable Integer id, @RequestBody Image image) {
		image.setId(id);
		Image updated = imageService.partialUpdate(image);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		imageService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
