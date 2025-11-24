package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Gender;
import group.sneakers.sneakers.repository.products.GenderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class GenderService {

	@Autowired
	private GenderRepository genderRepository;

	public List<Gender> findAll() {
		return genderRepository.findAll();
	}

	public Gender findById(Integer id) {
		return genderRepository.findById(id).orElse(null);
	}

	public Gender save(Gender gender) {
		return genderRepository.save(gender);
	}

	public Gender partialUpdate(Gender gender) {
		Gender existing = genderRepository.findById(gender.getId()).orElse(null);
		if (existing != null) {
			if (gender.getName() != null) existing.setName(gender.getName());
			return genderRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		genderRepository.deleteById(id);
	}

}
