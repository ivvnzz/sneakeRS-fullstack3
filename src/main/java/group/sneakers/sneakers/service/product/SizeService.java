package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Size;
import group.sneakers.sneakers.repository.products.SizeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class SizeService {

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private SizesService sizesService;

	public List<Size> findAll() {
		return sizeRepository.findAll();
	}

	public Size findById(Integer id) {
		return sizeRepository.findById(id).orElse(null);
	}

	public Size save(Size s) {
		return sizeRepository.save(s);
	}

	public Size partialUpdate(Size s) {
		Size existing = sizeRepository.findById(s.getId()).orElse(null);
		if (existing != null) {
			if (s.getNumber() != null) existing.setNumber(s.getNumber());
			if (s.getDescription() != null) existing.setDescription(s.getDescription());
			return sizeRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		List<group.sneakers.sneakers.model.products.Sizes> list = sizesService.findAll();
		for (group.sneakers.sneakers.model.products.Sizes s : list) {
			if (s.getSize() != null && s.getSize().getId().equals(id)) {
			}
		}
		sizeRepository.deleteById(id);
	}

}
