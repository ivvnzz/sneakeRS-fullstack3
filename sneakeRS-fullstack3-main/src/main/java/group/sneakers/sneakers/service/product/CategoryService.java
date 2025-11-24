package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Category;
import group.sneakers.sneakers.repository.products.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoriesService categoriesService;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Category partialUpdate(Category category) {
		Category existing = categoryRepository.findById(category.getId()).orElse(null);
		if (existing != null) {
			if (category.getName() != null) existing.setName(category.getName());
			return categoryRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		// hace que no se pueda eliminar una categoria si esta asociada a un producto
		List<group.sneakers.sneakers.model.products.Categories> list = categoriesService.findAll();
		for (group.sneakers.sneakers.model.products.Categories c : list) {
			if (c.getCategory() != null && c.getCategory().getId().equals(id)) {
			}
		}
		categoryRepository.deleteById(id);
	}

}
