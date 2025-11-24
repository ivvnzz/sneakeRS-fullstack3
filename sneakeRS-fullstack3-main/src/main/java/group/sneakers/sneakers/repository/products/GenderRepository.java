package group.sneakers.sneakers.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.products.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {

}
