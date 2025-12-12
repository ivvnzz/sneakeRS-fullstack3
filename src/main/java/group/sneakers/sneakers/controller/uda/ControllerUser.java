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

import group.sneakers.sneakers.service.uda.UserService;
import group.sneakers.sneakers.model.uda.User;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> list = userService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Integer id) {
		User user = userService.findById(id);
		if (user == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		User created = userService.save(user);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
		user.setId(id);
		User updated = userService.save(user);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> partial(@PathVariable Integer id, @RequestBody User user) {
		user.setId(id);
		User updated = userService.partialUpdate(user);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
