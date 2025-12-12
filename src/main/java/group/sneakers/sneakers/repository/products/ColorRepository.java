package group.sneakers.sneakers.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.products.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

}
