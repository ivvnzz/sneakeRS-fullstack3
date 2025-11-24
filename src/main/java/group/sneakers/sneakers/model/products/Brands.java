package group.sneakers.sneakers.model.products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Brands {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "brand", nullable = false)
	private Brand brand;

}
