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

import group.sneakers.sneakers.service.product.BrandService;
import group.sneakers.sneakers.model.products.Brand;

@RestController
@RequestMapping("/api/brands")
public class ControllerBrand {

	@Autowired
	private BrandService brandService;

	@GetMapping
	public ResponseEntity<List<Brand>> getAll() {
		List<Brand> list = brandService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Brand> getById(@PathVariable Integer id) {
		Brand brand = brandService.findById(id);
		if (brand == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(brand);
	}

	@PostMapping
	public ResponseEntity<Brand> create(@RequestBody Brand brand) {
		Brand created = brandService.save(brand);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Brand> update(@PathVariable Integer id, @RequestBody Brand brand) {
		brand.setId(id);
		Brand updated = brandService.save(brand);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Brand> partial(@PathVariable Integer id, @RequestBody Brand brand) {
		brand.setId(id);
		Brand updated = brandService.partialUpdate(brand);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		brandService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
