package group.sneakers.sneakers.controller.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.sneakers.sneakers.service.sales.SalesService;
import group.sneakers.sneakers.service.sales.TokenService;
import group.sneakers.sneakers.repository.sales.StatusRepository;
import group.sneakers.sneakers.repository.sales.PaymentMetodRepository;
import group.sneakers.sneakers.repository.sales.ShippingMetodRepository;
import group.sneakers.sneakers.repository.uda.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import group.sneakers.sneakers.model.sales.Sales;

@RestController
@RequestMapping("/api/sales")
public class ControllerSales {

	@Autowired
	private SalesService salesService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private PaymentMetodRepository paymentMetodRepository;

	@Autowired
	private ShippingMetodRepository shippingMetodRepository;

    private static final Logger logger = LoggerFactory.getLogger(ControllerSales.class);

	@Value("${ADMIN_KEY:}")
	private String adminKey;

	@GetMapping
	public ResponseEntity<List<Sales>> getAll() {
		List<Sales> list = salesService.findAll();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sales> getById(@PathVariable Integer id) {
		Sales sales = salesService.findById(id);
		if (sales == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(sales);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Sales sales) {
		if (sales != null && sales.getUser() != null && sales.getUser().getId() != null) {
			Integer userId = sales.getUser().getId();
			if (!userRepository.existsById(userId)) {
				java.util.Map<String, Object> m = new java.util.HashMap<>();
				m.put("error", "User with id " + userId + " not found");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
			}
		}
		try {
			if (sales != null && sales.getStatus() != null && sales.getStatus().getId() != null) {
				Integer statusId = sales.getStatus().getId();
				if (!statusRepository.existsById(statusId)) {
					java.util.Map<String, Object> m = new java.util.HashMap<>();
					m.put("error", "Status with id " + statusId + " not found");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
				}
			}
			if (sales != null && sales.getPaymentMetod() != null && sales.getPaymentMetod().getId() != null) {
				Integer pmId = sales.getPaymentMetod().getId();
				if (!paymentMetodRepository.existsById(pmId)) {
					java.util.Map<String, Object> m = new java.util.HashMap<>();
					m.put("error", "PaymentMetod with id " + pmId + " not found");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
				}
			}
			if (sales != null && sales.getShippingMetod() != null && sales.getShippingMetod().getId() != null) {
				Integer smId = sales.getShippingMetod().getId();
				if (!shippingMetodRepository.existsById(smId)) {
					java.util.Map<String, Object> m = new java.util.HashMap<>();
					m.put("error", "ShippingMetod with id " + smId + " not found");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
				}
			}
			Sales created = null;
			try {
				created = salesService.save(sales);
			} catch (DataIntegrityViolationException dive) {
				logger.error("Data integrity violation while saving sale: {}", dive.getMessage(), dive);
				java.util.Map<String, Object> m = new java.util.HashMap<>();
				m.put("error", "Data integrity violation while saving sale: " + dive.getMostSpecificCause().getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
			}
			if (created == null) {
				logger.error("SalesService.save returned null for input: {}", sales);
				return ResponseEntity.status(500).body(java.util.Collections.singletonMap("error", "No se pudo crear la venta"));
			}
			String token = null;
			String warning = null;
			try {
				token = tokenService.generateAndStoreToken(created);
			} catch (DataIntegrityViolationException dive) {
				logger.error("Data integrity violation while creating sale: {}", dive.getMessage(), dive);
				java.util.Map<String, Object> m = new java.util.HashMap<>();
				m.put("error", "Data integrity violation: " + dive.getMostSpecificCause().getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
			} catch (Exception e) {
				warning = "No se pudo generar/guardar token: " + e.getMessage();
				logger.error("Token generation failed for sale {}: {}", created.getId(), e.getMessage(), e);
			}
			java.util.Map<String, Object> out = new java.util.HashMap<>();
			out.put("sale", created);
			out.put("token", token);
			if (warning != null) out.put("warning", warning);
			return ResponseEntity.status(201).body(out);
		} catch (Exception e) {
			logger.error("Error creating sale: {}", e.getMessage(), e);
			java.util.Map<String, Object> m = new java.util.HashMap<>();
			m.put("error", e.getMessage());
			return ResponseEntity.status(500).body(m);
		}
	}

	@GetMapping("/{id}/token")
	public ResponseEntity<java.util.Map<String, String>> getToken(@PathVariable Integer id) {
		String token = tokenService.findTokenBySalesId(id);
		if (token == null) return ResponseEntity.notFound().build();
		java.util.Map<String, String> out = new java.util.HashMap<>();
		out.put("token", token);
		return ResponseEntity.ok(out);
	}

	@GetMapping("/tokens")
	public ResponseEntity<java.util.List<TokenService.SaleToken>> listTokens() {
		java.util.List<TokenService.SaleToken> list = tokenService.findAllTokens();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/tokens/secure")
	public ResponseEntity<java.util.List<TokenService.SaleToken>> listTokensSecure(@RequestHeader(value = "X-ADMIN-KEY", required = false) String providedKey) {
		if (adminKey == null || adminKey.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin key not configured on server");
		}
		if (providedKey == null || !providedKey.equals(adminKey)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid admin key");
		}
		java.util.List<TokenService.SaleToken> list = tokenService.findAllTokens();
		if (list == null || list.isEmpty()) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Sales> update(@PathVariable Integer id, @RequestBody Sales sales) {
		sales.setId(id);
		Sales updated = salesService.save(sales);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Sales> partial(@PathVariable Integer id, @RequestBody Sales sales) {
		sales.setId(id);
		Sales updated = salesService.partialUpdate(sales);
		if (updated == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		salesService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
