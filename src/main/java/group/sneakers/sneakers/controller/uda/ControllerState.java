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

import group.sneakers.sneakers.service.uda.StateService;
import group.sneakers.sneakers.model.uda.State;

@RestController
@RequestMapping("/api/states")
public class ControllerState {

	@Autowired
	private StateService stateService;

	@GetMapping
	public ResponseEntity<List<State>> getAll() {
		List<State> list = stateService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<State> getById(@PathVariable Integer id) {
		State state = stateService.findById(id);
		if (state == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(state);
	}

	@PostMapping
	public ResponseEntity<State> create(@RequestBody State state) {
		State created = stateService.save(state);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<State> update(@PathVariable Integer id, @RequestBody State state) {
		state.setId(id);
		State updated = stateService.save(state);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<State> partial(@PathVariable Integer id, @RequestBody State state) {
		state.setId(id);
		State updated = stateService.partialUpdate(state);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		stateService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
