package group.sneakers.sneakers.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Materials;
import group.sneakers.sneakers.repository.products.MaterialsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MaterialsService {

	@Autowired
	private MaterialsRepository materialsRepository;

	public List<Materials> findAll() {
		return materialsRepository.findAll();
	}

	public Materials findById(Long id) {
		return materialsRepository.findById(id).orElse(null);
	}

	public Materials save(Materials m) {
		return materialsRepository.save(m);
	}

	public Materials partialUpdate(Materials m) {
		Materials existing = materialsRepository.findById(m.getId()).orElse(null);
		if (existing != null) {
			if (m.getProduct() != null) existing.setProduct(m.getProduct());
			if (m.getMaterial() != null) existing.setMaterial(m.getMaterial());
			return materialsRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Long id) {
		materialsRepository.deleteById(id);
	}

	public void deleteByProductId(Integer productId) {
		List<Materials> list = materialsRepository.findAll();
		for (Materials mm : list) {
			if (mm.getProduct() != null && mm.getProduct().getId().equals(productId)) {
				materialsRepository.deleteById(mm.getId());
			}
		}
	}

	public List<Materials> findByProductId(Integer productId) {
		List<Materials> out = new ArrayList<>();
		List<Materials> list = materialsRepository.findAll();
		for (Materials mm : list) {
			if (mm.getProduct() != null && mm.getProduct().getId().equals(productId)) {
				out.add(mm);
			}
		}
		return out;
	}

	public void deleteByMaterialId(Integer materialId) {
		List<Materials> list = materialsRepository.findAll();
		for (Materials m : list) {
			if (m.getMaterial() != null && m.getMaterial().getId().equals(materialId)) {
				materialsRepository.deleteById(m.getId());
			}
		}
	}

}
