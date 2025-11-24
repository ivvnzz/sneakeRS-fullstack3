package group.sneakers.sneakers.service.uda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.uda.State;
import group.sneakers.sneakers.repository.uda.StateRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CommuneService communeService;

	public List<State> findAll() {
		return stateRepository.findAll();
	}

	public State findById(Integer id) {
		return stateRepository.findById(id).orElse(null);
	}

	public State save(State state) {
		return stateRepository.save(state);
	}

	public State partialUpdate(State state) {
		State existing = stateRepository.findById(state.getId()).orElse(null);
		if (existing != null) {
			if (state.getName() != null) existing.setName(state.getName());
			if (state.getDescription() != null) existing.setDescription(state.getDescription());
			return stateRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		communeService.deleteByStateId(id);
		stateRepository.deleteById(id);
	}

}
