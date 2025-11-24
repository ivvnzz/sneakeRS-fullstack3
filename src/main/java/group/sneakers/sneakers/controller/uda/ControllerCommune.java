package group.sneakers.sneakers.controller.uda;

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

import group.sneakers.sneakers.service.uda.CommuneService;
import group.sneakers.sneakers.model.uda.Commune;

@RestController
@RequestMapping("/api/communes")
public class ControllerCommune {

	@Autowired
	private CommuneService communeService;

	@GetMapping
	public ResponseEntity<List<Commune>> getAll() {
		List<Commune> list = communeService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Commune> getById(@PathVariable Integer id) {
		Commune commune = communeService.findById(id);
		if (commune == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(commune);
	}

	@PostMapping
	public ResponseEntity<Commune> create(@RequestBody Commune commune) {
		Commune created = communeService.save(commune);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Commune> update(@PathVariable Integer id, @RequestBody Commune commune) {
		commune.setId(id);
		Commune updated = communeService.save(commune);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Commune> partial(@PathVariable Integer id, @RequestBody Commune commune) {
		commune.setId(id);
		Commune updated = communeService.partialUpdate(commune);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		communeService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
