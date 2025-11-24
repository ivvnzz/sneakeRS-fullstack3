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

import group.sneakers.sneakers.service.product.GenderService;
import group.sneakers.sneakers.model.products.Gender;

@RestController
@RequestMapping("/api/genders")
public class ControllerGender {

	@Autowired
	private GenderService genderService;

	@GetMapping
	public ResponseEntity<List<Gender>> getAll() {
		List<Gender> list = genderService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Gender> getById(@PathVariable Integer id) {
		Gender gender = genderService.findById(id);
		if (gender == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(gender);
	}

	@PostMapping
	public ResponseEntity<Gender> create(@RequestBody Gender gender) {
		Gender created = genderService.save(gender);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Gender> update(@PathVariable Integer id, @RequestBody Gender gender) {
		gender.setId(id);
		Gender updated = genderService.save(gender);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Gender> partial(@PathVariable Integer id, @RequestBody Gender gender) {
		gender.setId(id);
		Gender updated = genderService.partialUpdate(gender);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		genderService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
