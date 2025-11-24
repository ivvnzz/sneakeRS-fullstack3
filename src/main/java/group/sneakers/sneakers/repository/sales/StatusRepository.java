package group.sneakers.sneakers.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.sales.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
