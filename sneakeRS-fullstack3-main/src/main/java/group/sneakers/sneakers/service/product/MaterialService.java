package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Material;
import group.sneakers.sneakers.repository.products.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private MaterialsService materialsService;

	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	public Material findById(Integer id) {
		return materialRepository.findById(id).orElse(null);
	}

	public Material save(Material m) {
		return materialRepository.save(m);
	}

	public Material partialUpdate(Material m) {
		Material existing = materialRepository.findById(m.getId()).orElse(null);
		if (existing != null) {
			if (m.getName() != null) existing.setName(m.getName());
			if (m.getDescription() != null) existing.setDescription(m.getDescription());
			return materialRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		// hace que no se pueda eliminar un material si esta asociado a un producto
		List<group.sneakers.sneakers.model.products.Materials> list = materialsService.findAll();
		for (group.sneakers.sneakers.model.products.Materials m : list) {
			if (m.getMaterial() != null && m.getMaterial().getId().equals(id)) {
			}
		}
		materialRepository.deleteById(id);
	}

}
