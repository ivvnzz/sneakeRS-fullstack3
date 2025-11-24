package group.sneakers.sneakers.service.uda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.uda.Commune;
import group.sneakers.sneakers.repository.uda.CommuneRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CommuneService {

	@Autowired
	private CommuneRepository communeRepository;

	@Autowired
	private AddressService addressService;

	public List<Commune> findAll() {
		return communeRepository.findAll();
	}

	public Commune findById(Integer id) {
		return communeRepository.findById(id).orElse(null);
	}

	public Commune save(Commune commune) {
		return communeRepository.save(commune);
	}

	public Commune partialUpdate(Commune commune) {
		Commune existing = communeRepository.findById(commune.getId()).orElse(null);
		if (existing != null) {
			if (commune.getName() != null) existing.setName(commune.getName());
			if (commune.getDescription() != null) existing.setDescription(commune.getDescription());
			if (commune.getState() != null) existing.setState(commune.getState());
			return communeRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		addressService.deleteByCommuneId(id);
		communeRepository.deleteById(id);
	}

	public void deleteByStateId(Integer stateId) {
		List<Commune> list = communeRepository.findAll();
		for (Commune c : list) {
			if (c.getState() != null && c.getState().getId().equals(stateId)) {
				deleteById(c.getId());
			}
		}
	}

	public List<Commune> findByStateId(Integer stateId) {
		List<Commune> out = new ArrayList<>();
		List<Commune> list = communeRepository.findAll();
		for (Commune c : list) {
			if (c.getState() != null && c.getState().getId().equals(stateId)) {
				out.add(c);
			}
		}
		return out;
	}

}
