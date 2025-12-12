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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import group.sneakers.sneakers.service.uda.RolService;
import group.sneakers.sneakers.model.uda.Rol;

@RestController
@RequestMapping("/api/roles")
public class ControllerRol {

	@Autowired
	private RolService rolService;

	@GetMapping
	public ResponseEntity<List<Rol>> getAll() {
		List<Rol> list = rolService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rol> getById(@PathVariable Integer id) {
		Rol rol = rolService.findById(id);
		if (rol == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rol);
	}

	@PostMapping
	public ResponseEntity<Rol> create(@RequestBody Rol rol) {
		Rol created = rolService.save(rol);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Rol> update(@PathVariable Integer id, @RequestBody Rol rol) {
		rol.setId(id);
		Rol updated = rolService.save(rol);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Rol> partial(@PathVariable Integer id, @RequestBody Rol rol) {
		rol.setId(id);
		Rol updated = rolService.partialUpdate(rol);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		rolService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
