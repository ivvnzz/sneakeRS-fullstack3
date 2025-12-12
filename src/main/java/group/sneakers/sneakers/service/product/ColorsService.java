package group.sneakers.sneakers.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Colors;
import group.sneakers.sneakers.repository.products.ColorsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ColorsService {

	@Autowired
	private ColorsRepository colorsRepository;

	public List<Colors> findAll() {
		return colorsRepository.findAll();
	}

	public Colors findById(Long id) {
		return colorsRepository.findById(id).orElse(null);
	}

	public Colors save(Colors c) {
		return colorsRepository.save(c);
	}

	public Colors partialUpdate(Colors c) {
		Colors existing = colorsRepository.findById(c.getId()).orElse(null);
		if (existing != null) {
			if (c.getProduct() != null) existing.setProduct(c.getProduct());
			if (c.getColor() != null) existing.setColor(c.getColor());
			return colorsRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Long id) {
		colorsRepository.deleteById(id);
	}

	public void deleteByProductId(Integer productId) {
		List<Colors> list = colorsRepository.findAll();
		for (Colors cc : list) {
			if (cc.getProduct() != null && cc.getProduct().getId().equals(productId)) {
				colorsRepository.deleteById(cc.getId());
			}
		}
	}

	public List<Colors> findByProductId(Integer productId) {
		List<Colors> out = new ArrayList<>();
		List<Colors> list = colorsRepository.findAll();
		for (Colors cc : list) {
			if (cc.getProduct() != null && cc.getProduct().getId().equals(productId)) {
				out.add(cc);
			}
		}
		return out;
	}

	public void deleteByColorId(Integer colorId) {
		List<Colors> list = colorsRepository.findAll();
		for (Colors c : list) {
			if (c.getColor() != null && c.getColor().getId().equals(colorId)) {
				colorsRepository.deleteById(c.getId());
			}
		}
	}

}
