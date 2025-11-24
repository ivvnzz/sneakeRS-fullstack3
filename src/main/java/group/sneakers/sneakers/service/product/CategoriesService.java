package group.sneakers.sneakers.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Categories;
import group.sneakers.sneakers.repository.products.CategoriesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;

	public List<Categories> findAll() {
		return categoriesRepository.findAll();
	}

	public Categories findById(Long id) {
		return categoriesRepository.findById(id).orElse(null);
	}

	public Categories save(Categories c) {
		return categoriesRepository.save(c);
	}

	public Categories partialUpdate(Categories c) {
		Categories existing = categoriesRepository.findById(c.getId()).orElse(null);
		if (existing != null) {
			if (c.getProduct() != null) existing.setProduct(c.getProduct());
			if (c.getCategory() != null) existing.setCategory(c.getCategory());
			return categoriesRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Long id) {
		categoriesRepository.deleteById(id);
	}

	public void deleteByProductId(Integer productId) {
		List<Categories> list = categoriesRepository.findAll();
		for (Categories cc : list) {
			if (cc.getProduct() != null && cc.getProduct().getId().equals(productId)) {
				categoriesRepository.deleteById(cc.getId());
			}
		}
	}

	public List<Categories> findByProductId(Integer productId) {
		List<Categories> out = new ArrayList<>();
		List<Categories> list = categoriesRepository.findAll();
		for (Categories cc : list) {
			if (cc.getProduct() != null && cc.getProduct().getId().equals(productId)) {
				out.add(cc);
			}
		}
		return out;
	}

	public void deleteByCategoryId(Integer categoryId) {
		List<Categories> list = categoriesRepository.findAll();
		for (Categories c : list) {
			if (c.getCategory() != null && c.getCategory().getId().equals(categoryId)) {
				categoriesRepository.deleteById(c.getId());
			}
		}
	}

}
