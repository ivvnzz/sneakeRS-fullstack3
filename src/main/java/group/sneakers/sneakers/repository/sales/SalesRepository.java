package group.sneakers.sneakers.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.sales.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

}
