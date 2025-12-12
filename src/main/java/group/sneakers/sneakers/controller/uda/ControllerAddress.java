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

import group.sneakers.sneakers.service.uda.AddressService;
import group.sneakers.sneakers.model.uda.Address;

@RestController
@RequestMapping("/api/addresses")
public class ControllerAddress {

	@Autowired
	private AddressService addressService;

	@GetMapping
	public ResponseEntity<List<Address>> getAll() {
		List<Address> list = addressService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> getById(@PathVariable Integer id) {
		Address address = addressService.findById(id);
		if (address == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(address);
	}

	@PostMapping
	public ResponseEntity<Address> create(@RequestBody Address address) {
		Address created = addressService.save(address);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Address> update(@PathVariable Integer id, @RequestBody Address address) {
		address.setId(id);
		Address updated = addressService.save(address);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Address> partial(@PathVariable Integer id, @RequestBody Address address) {
		address.setId(id);
		Address updated = addressService.partialUpdate(address);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		addressService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
