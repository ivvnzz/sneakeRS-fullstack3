package group.sneakers.sneakers.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.sales.ProductSales;

@Repository
public interface ProductSalesRepository extends JpaRepository<ProductSales, Integer> {

}
