package group.sneakers.sneakers.service.uda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.uda.Rol;
import group.sneakers.sneakers.repository.uda.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RolService {

	@Autowired
	private RolRepository rolRepository;

	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	public Rol findById(Integer id) {
		return rolRepository.findById(id).orElse(null);
	}

	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}

	public Rol partialUpdate(Rol rol) {
		Rol existing = rolRepository.findById(rol.getId()).orElse(null);
		if (existing != null) {
			if (rol.getName() != null) {
				existing.setName(rol.getName());
			}
			return rolRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		rolRepository.deleteById(id);
	}

}
