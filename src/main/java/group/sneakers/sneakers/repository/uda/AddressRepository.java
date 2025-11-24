package group.sneakers.sneakers.repository.uda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.uda.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
