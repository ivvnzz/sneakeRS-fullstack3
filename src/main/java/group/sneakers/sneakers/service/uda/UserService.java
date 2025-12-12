package group.sneakers.sneakers.service.uda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.uda.User;
import group.sneakers.sneakers.repository.uda.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressService addressService;

	@Autowired
	private group.sneakers.sneakers.service.sales.SalesService salesService;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User partialUpdate(User user) {
		User existing = userRepository.findById(user.getId()).orElse(null);
		if (existing != null) {
			if (user.getFirstName() != null) existing.setFirstName(user.getFirstName());
			if (user.getLastName() != null) existing.setLastName(user.getLastName());
			if (user.getEmail() != null) existing.setEmail(user.getEmail());
			if (user.getPassword() != null) existing.setPassword(user.getPassword());
			if (user.getRol() != null) existing.setRol(user.getRol());
			return userRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		//eliminacion de direcciones y ventas asociadas al usuario
		addressService.deleteByUserId(id);
		salesService.deleteByUserId(id);
		userRepository.deleteById(id);
	}

	public void deleteByRolId(Integer rolId) {
		List<User> users = userRepository.findAll();
		for (User u : users) {
			if (u.getRol() != null && u.getRol().getId().equals(rolId)) {
				deleteById(u.getId());
			}
		}
	}

	public List<User> findByRolId(Integer rolId) {
		List<User> out = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User u : users) {
			if (u.getRol() != null && u.getRol().getId().equals(rolId)) {
				out.add(u);
			}
		}
		return out;
	}

}
