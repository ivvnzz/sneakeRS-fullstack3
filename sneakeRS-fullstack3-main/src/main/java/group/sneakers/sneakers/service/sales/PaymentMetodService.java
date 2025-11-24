package group.sneakers.sneakers.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.sales.PaymentMetod;
import group.sneakers.sneakers.repository.sales.PaymentMetodRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class PaymentMetodService {

	@Autowired
	private PaymentMetodRepository paymentMetodRepository;

	public List<PaymentMetod> findAll() {
		return paymentMetodRepository.findAll();
	}

	public PaymentMetod findById(Integer id) {
		return paymentMetodRepository.findById(id).orElse(null);
	}

	public PaymentMetod save(PaymentMetod p) {
		return paymentMetodRepository.save(p);
	}

	public PaymentMetod partialUpdate(PaymentMetod p) {
		PaymentMetod existing = paymentMetodRepository.findById(p.getId()).orElse(null);
		if (existing != null) {
			if (p.getName() != null) existing.setName(p.getName());
			return paymentMetodRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		paymentMetodRepository.deleteById(id);
	}

}
