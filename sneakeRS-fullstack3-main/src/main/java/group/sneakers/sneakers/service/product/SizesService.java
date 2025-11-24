package group.sneakers.sneakers.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Sizes;
import group.sneakers.sneakers.repository.products.SizesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class SizesService {

	@Autowired
	private SizesRepository sizesRepository;

	public List<Sizes> findAll() {
		return sizesRepository.findAll();
	}

	public Sizes findById(Long id) {
		return sizesRepository.findById(id).orElse(null);
	}

	public Sizes save(Sizes s) {
		return sizesRepository.save(s);
	}

	public Sizes partialUpdate(Sizes s) {
		Sizes existing = sizesRepository.findById(s.getId()).orElse(null);
		if (existing != null) {
			if (s.getProduct() != null) existing.setProduct(s.getProduct());
			if (s.getSize() != null) existing.setSize(s.getSize());
			return sizesRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Long id) {
		sizesRepository.deleteById(id);
	}

	public void deleteByProductId(Integer productId) {
		List<Sizes> list = sizesRepository.findAll();
		for (Sizes ss : list) {
			if (ss.getProduct() != null && ss.getProduct().getId().equals(productId)) {
				sizesRepository.deleteById(ss.getId());
			}
		}
	}

	public List<Sizes> findByProductId(Integer productId) {
		List<Sizes> out = new ArrayList<>();
		List<Sizes> list = sizesRepository.findAll();
		for (Sizes ss : list) {
			if (ss.getProduct() != null && ss.getProduct().getId().equals(productId)) {
				out.add(ss);
			}
		}
		return out;
	}

	public void deleteBySizeId(Integer sizeId) {
		List<Sizes> list = sizesRepository.findAll();
		for (Sizes s : list) {
			if (s.getSize() != null && s.getSize().getId().equals(sizeId)) {
				sizesRepository.deleteById(s.getId());
			}
		}
	}

}
