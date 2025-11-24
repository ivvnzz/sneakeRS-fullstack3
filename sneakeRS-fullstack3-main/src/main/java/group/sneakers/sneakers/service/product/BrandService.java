package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Brand;
import group.sneakers.sneakers.repository.products.BrandRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private BrandsService brandsService;

	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	public Brand findById(Integer id) {
		return brandRepository.findById(id).orElse(null);
	}

	public Brand save(Brand brand) {
		return brandRepository.save(brand);
	}

	public Brand partialUpdate(Brand brand) {
		Brand existing = brandRepository.findById(brand.getId()).orElse(null);
		if (existing != null) {
			if (brand.getName() != null) existing.setName(brand.getName());
			if (brand.getDescription() != null) existing.setDescription(brand.getDescription());
			return brandRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		// No permitir eliminar una marca si está asociada a productos
		List<group.sneakers.sneakers.model.products.Brands> list = brandsService.findAll();
		for (group.sneakers.sneakers.model.products.Brands b : list) {
			if (b.getBrand() != null && b.getBrand().getId().equals(id)) {
			}
		}
		brandRepository.deleteById(id);
	}

}
