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

import group.sneakers.sneakers.service.sales.ShippingMetodService;
import group.sneakers.sneakers.model.sales.ShippingMetod;

@RestController
@RequestMapping("/api/shipping-metod")
public class ControllerShippingMetod {

    @Autowired
    private ShippingMetodService shippingMetodService;

    @GetMapping
    public ResponseEntity<List<ShippingMetod>> getAll() {
        List<ShippingMetod> list = shippingMetodService.findAll();
        if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingMetod> getById(@PathVariable Integer id) {
        ShippingMetod s = shippingMetodService.findById(id);
        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<ShippingMetod> create(@RequestBody ShippingMetod s) {
        ShippingMetod created = shippingMetodService.save(s);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingMetod> update(@PathVariable Integer id, @RequestBody ShippingMetod s) {
        s.setId(id);
        ShippingMetod updated = shippingMetodService.save(s);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ShippingMetod> partial(@PathVariable Integer id, @RequestBody ShippingMetod s) {
        s.setId(id);
        ShippingMetod updated = shippingMetodService.partialUpdate(s);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        shippingMetodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
