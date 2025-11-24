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

import group.sneakers.sneakers.service.sales.StatusService;
import group.sneakers.sneakers.model.sales.Status;

@RestController
@RequestMapping("/api/status")
public class ControllerStatus {

	@Autowired
	private StatusService statusService;

	@GetMapping
	public ResponseEntity<List<Status>> getAll() {
		List<Status> list = statusService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Status> getById(@PathVariable Integer id) {
		Status status = statusService.findById(id);
		if (status == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(status);
	}

	@PostMapping
	public ResponseEntity<Status> create(@RequestBody Status status) {
		Status created = statusService.save(status);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Status> update(@PathVariable Integer id, @RequestBody Status status) {
		status.setId(id);
		Status updated = statusService.save(status);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Status> partial(@PathVariable Integer id, @RequestBody Status status) {
		status.setId(id);
		Status updated = statusService.partialUpdate(status);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		statusService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
