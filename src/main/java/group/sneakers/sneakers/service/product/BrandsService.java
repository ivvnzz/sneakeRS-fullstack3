package group.sneakers.sneakers.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Brands;
import group.sneakers.sneakers.repository.products.BrandsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class BrandsService {

	@Autowired
	private BrandsRepository brandsRepository;

	public List<Brands> findAll() {
		return brandsRepository.findAll();
	}

	public Brands findById(Long id) {
		return brandsRepository.findById(id).orElse(null);
	}

	public Brands save(Brands brands) {
		return brandsRepository.save(brands);
	}

	public Brands partialUpdate(Brands brands) {
		Brands existing = brandsRepository.findById(brands.getId()).orElse(null);
		if (existing != null) {
			if (brands.getProduct() != null) existing.setProduct(brands.getProduct());
			if (brands.getBrand() != null) existing.setBrand(brands.getBrand());
			return brandsRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Long id) {
		brandsRepository.deleteById(id);
	}

	public void deleteByProductId(Integer productId) {
		List<Brands> list = brandsRepository.findAll();
		for (Brands brands : list) {
			if (brands.getProduct() != null && brands.getProduct().getId().equals(productId)) {
				brandsRepository.deleteById(brands.getId());
			}
		}
	}

	public List<Brands> findByProductId(Integer productId) {
		List<Brands> out = new ArrayList<>();
		List<Brands> list = brandsRepository.findAll();
		for (Brands brands : list) {
			if (brands.getProduct() != null && brands.getProduct().getId().equals(productId)) {
				out.add(brands);
			}
		}
		return out;
	}

	public void deleteByBrandId(Integer brandId) {
		List<Brands> list = brandsRepository.findAll();
		for (Brands b : list) {
			if (b.getBrand() != null && b.getBrand().getId().equals(brandId)) {
				brandsRepository.deleteById(b.getId());
			}
		}
	}

}
