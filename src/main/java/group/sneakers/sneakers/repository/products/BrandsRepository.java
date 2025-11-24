package group.sneakers.sneakers.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.products.Brands;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Long> {

}
