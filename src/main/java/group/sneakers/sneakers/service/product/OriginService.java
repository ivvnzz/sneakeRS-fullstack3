package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Origin;
import group.sneakers.sneakers.repository.products.OriginRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class OriginService {

	@Autowired
	private OriginRepository originRepository;

	public List<Origin> findAll() {
		return originRepository.findAll();
	}

	public Origin findById(Integer id) {
		return originRepository.findById(id).orElse(null);
	}

	public Origin save(Origin o) {
		return originRepository.save(o);
	}

	public Origin partialUpdate(Origin o) {
		Origin existing = originRepository.findById(o.getId()).orElse(null);
		if (existing != null) {
			if (o.getCountry() != null) existing.setCountry(o.getCountry());
			if (o.getDescription() != null) existing.setDescription(o.getDescription());
			return originRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		originRepository.deleteById(id);
	}

}
