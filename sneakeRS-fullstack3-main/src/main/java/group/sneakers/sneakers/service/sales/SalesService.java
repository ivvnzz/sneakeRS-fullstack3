package group.sneakers.sneakers.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.sales.Sales;
import group.sneakers.sneakers.repository.sales.SalesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class SalesService {

	@Autowired
	private SalesRepository salesRepository;

	@Autowired
	private ProductSalesService productSalesService;

	public List<Sales> findAll() {
		return salesRepository.findAll();
	}

	public Sales findById(Integer id) {
		return salesRepository.findById(id).orElse(null);
	}

	public Sales save(Sales s) {
		return salesRepository.save(s);
	}

	public Sales partialUpdate(Sales s) {
		Sales existing = salesRepository.findById(s.getId()).orElse(null);
		if (existing != null) {
			if (s.getDate() != null) existing.setDate(s.getDate());
			if (s.getTotal() != null) existing.setTotal(s.getTotal());
			if (s.getUser() != null) existing.setUser(s.getUser());
			if (s.getStatus() != null) existing.setStatus(s.getStatus());
			if (s.getPaymentMetod() != null) existing.setPaymentMetod(s.getPaymentMetod());
			if (s.getShippingMetod() != null) existing.setShippingMetod(s.getShippingMetod());
			return salesRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		productSalesService.deleteBySalesId(id);
		salesRepository.deleteById(id);
	}

	public void deleteByUserId(Integer userId) {
		List<Sales> list = salesRepository.findAll();
		for (Sales s : list) {
			if (s.getUser() != null && s.getUser().getId().equals(userId)) {
				productSalesService.deleteBySalesId(s.getId());
				salesRepository.deleteById(s.getId());
			}
		}
	}

}
