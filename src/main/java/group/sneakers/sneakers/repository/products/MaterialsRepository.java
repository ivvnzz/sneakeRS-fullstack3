package group.sneakers.sneakers.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.products.Materials;

@Repository
public interface MaterialsRepository extends JpaRepository<Materials, Long> {

}
