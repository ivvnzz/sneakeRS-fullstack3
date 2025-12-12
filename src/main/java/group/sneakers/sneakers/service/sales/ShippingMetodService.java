package group.sneakers.sneakers.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.sales.Sales;
import group.sneakers.sneakers.model.sales.ShippingMetod;
import group.sneakers.sneakers.repository.sales.ShippingMetodRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ShippingMetodService {

    @Autowired
    private ShippingMetodRepository shippingMetodRepository;

    @Autowired
    private SalesService salesService;

    public List<ShippingMetod> findAll() {
        return shippingMetodRepository.findAll();
    }

    public ShippingMetod findById(Integer id) {
        return shippingMetodRepository.findById(id).orElse(null);
    }

    public ShippingMetod save(ShippingMetod s) {
        return shippingMetodRepository.save(s);
    }

    public ShippingMetod partialUpdate(ShippingMetod s) {
        ShippingMetod existing = shippingMetodRepository.findById(s.getId()).orElse(null);
        if (existing != null) {
            if (s.getName() != null) existing.setName(s.getName());
            if (s.getDescription() != null) existing.setDescription(s.getDescription());
            return shippingMetodRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        List<Sales> list = salesService.findAll();
        for (Sales sale : list) {
            if (sale.getShippingMetod() != null && sale.getShippingMetod().getId().equals(id)) {

            }
        }
        shippingMetodRepository.deleteById(id);
    }

}
