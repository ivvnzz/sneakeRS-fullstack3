package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Color;
import group.sneakers.sneakers.repository.products.ColorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ColorService {

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private ColorsService colorsService;

	public List<Color> findAll() {
		return colorRepository.findAll();
	}

	public Color findById(Long id) {
		return colorRepository.findById(id).orElse(null);
	}

	public Color save(Color color) {
		return colorRepository.save(color);
	}

	public Color partialUpdate(Color color) {
		Color existing = colorRepository.findById(color.getId()).orElse(null);
		if (existing != null) {
			if (color.getName() != null) existing.setName(color.getName());
			return colorRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Long id) {
		List<group.sneakers.sneakers.model.products.Colors> list = colorsService.findAll();
		for (group.sneakers.sneakers.model.products.Colors c : list) {
			if (c.getColor() != null && c.getColor().getId().equals(id)) {
			}
		}
		colorRepository.deleteById(id);
	}

}
