package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Product;
import group.sneakers.sneakers.repository.products.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandsService brandsService;

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ColorsService colorsService;

	@Autowired
	private MaterialsService materialsService;

	@Autowired
	private SizesService sizesService;

	@Autowired
	private ImagesService imagesService;

	@Autowired
	private group.sneakers.sneakers.service.sales.ProductSalesService productSalesService;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product save(Product p) {
		return productRepository.save(p);
	}

	public Product partialUpdate(Product p) {
		Product existing = productRepository.findById(p.getId()).orElse(null);
		if (existing != null) {
			if (p.getName() != null) existing.setName(p.getName());
			if (p.getDescription() != null) existing.setDescription(p.getDescription());
			if (p.getPrice() != null) existing.setPrice(p.getPrice());
			if (p.getGender() != null) existing.setGender(p.getGender());
			return productRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		brandsService.deleteByProductId(id);
		categoriesService.deleteByProductId(id);
		colorsService.deleteByProductId(id);
		materialsService.deleteByProductId(id);
		sizesService.deleteByProductId(id);
		imagesService.deleteByProductId(id);
		productSalesService.deleteByProductId(id);
		productRepository.deleteById(id);
	}

}
