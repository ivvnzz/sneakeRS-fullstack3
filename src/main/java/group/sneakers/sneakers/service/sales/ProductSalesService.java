package group.sneakers.sneakers.service.sales;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.sales.ProductSales;
import group.sneakers.sneakers.repository.sales.ProductSalesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ProductSalesService {

	@Autowired
	private ProductSalesRepository productSalesRepository;

	public List<ProductSales> findAll() {
		return productSalesRepository.findAll();
	}

	public ProductSales findById(Integer id) {
		return productSalesRepository.findById(id).orElse(null);
	}

	public ProductSales save(ProductSales ps) {
		return productSalesRepository.save(ps);
	}

	public ProductSales partialUpdate(ProductSales ps) {
		ProductSales existing = productSalesRepository.findById(ps.getId()).orElse(null);
		if (existing != null) {
			if (ps.getQuantity() != null) existing.setQuantity(ps.getQuantity());
			if (ps.getUnitaryPrice() != null) existing.setUnitaryPrice(ps.getUnitaryPrice());
			if (ps.getSales() != null) existing.setSales(ps.getSales());
			if (ps.getProduct() != null) existing.setProduct(ps.getProduct());
			return productSalesRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		productSalesRepository.deleteById(id);
	}

	public void deleteBySalesId(Integer salesId) {
		List<ProductSales> list = productSalesRepository.findAll();
		for (ProductSales ps : list) {
			if (ps.getSales() != null && ps.getSales().getId().equals(salesId)) {
				productSalesRepository.deleteById(ps.getId());
			}
		}
	}

	public void deleteByProductId(Integer productId) {
		List<ProductSales> list = productSalesRepository.findAll();
		for (ProductSales ps : list) {
			if (ps.getProduct() != null && ps.getProduct().getId().equals(productId)) {
				productSalesRepository.deleteById(ps.getId());
			}
		}
	}

	public List<ProductSales> findBySalesId(Integer salesId) {
		List<ProductSales> out = new ArrayList<>();
		List<ProductSales> list = productSalesRepository.findAll();
		for (ProductSales ps : list) {
			if (ps.getSales() != null && ps.getSales().getId().equals(salesId)) {
				out.add(ps);
			}
		}
		return out;
	}

	public List<ProductSales> findByProductId(Integer productId) {
		List<ProductSales> out = new ArrayList<>();
		List<ProductSales> list = productSalesRepository.findAll();
		for (ProductSales ps : list) {
			if (ps.getProduct() != null && ps.getProduct().getId().equals(productId)) {
				out.add(ps);
			}
		}
		return out;
	}

}
