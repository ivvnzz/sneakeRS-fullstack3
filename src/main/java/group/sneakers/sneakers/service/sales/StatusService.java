package group.sneakers.sneakers.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.sales.Status;
import group.sneakers.sneakers.repository.sales.StatusRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;

	public List<Status> findAll() {
		return statusRepository.findAll();
	}

	public Status findById(Integer id) {
		return statusRepository.findById(id).orElse(null);
	}

	public Status save(Status s) {
		return statusRepository.save(s);
	}

	public Status partialUpdate(Status s) {
		Status existing = statusRepository.findById(s.getId()).orElse(null);
		if (existing != null) {
			if (s.getName() != null) existing.setName(s.getName());
			return statusRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		statusRepository.deleteById(id);
	}

}
