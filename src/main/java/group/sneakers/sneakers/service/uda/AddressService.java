package group.sneakers.sneakers.service.uda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.uda.Address;
import group.sneakers.sneakers.repository.uda.AddressRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	public Address findById(Integer id) {
		return addressRepository.findById(id).orElse(null);
	}

	public Address save(Address address) {
		return addressRepository.save(address);
	}

	public Address partialUpdate(Address address) {
		Address existing = addressRepository.findById(address.getId()).orElse(null);
		if (existing != null) {
			if (address.getStreet() != null) existing.setStreet(address.getStreet());
			if (address.getNumber() != null) existing.setNumber(address.getNumber());
			if (address.getZipCode() != null) existing.setZipCode(address.getZipCode());
			if (address.getUser() != null) existing.setUser(address.getUser());
			if (address.getCommune() != null) existing.setCommune(address.getCommune());
			return addressRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		addressRepository.deleteById(id);
	}

	public void deleteByUserId(Integer userId) {
		List<Address> list = addressRepository.findAll();
		for (Address a : list) {
			if (a.getUser() != null && a.getUser().getId().equals(userId)) {
				addressRepository.deleteById(a.getId());
			}
		}
	}

	public void deleteByCommuneId(Integer communeId) {
		List<Address> list = addressRepository.findAll();
		for (Address a : list) {
			if (a.getCommune() != null && a.getCommune().getId().equals(communeId)) {
				addressRepository.deleteById(a.getId());
			}
		}
	}

	public List<Address> findByUserId(Integer userId) {
		List<Address> out = new ArrayList<>();
		List<Address> list = addressRepository.findAll();
		for (Address a : list) {
			if (a.getUser() != null && a.getUser().getId().equals(userId)) {
				out.add(a);
			}
		}
		return out;
	}

	public List<Address> findByCommuneId(Integer communeId) {
		List<Address> out = new ArrayList<>();
		List<Address> list = addressRepository.findAll();
		for (Address a : list) {
			if (a.getCommune() != null && a.getCommune().getId().equals(communeId)) {
				out.add(a);
			}
		}
		return out;
	}

}
