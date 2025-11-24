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
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Colors {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "color", nullable = false)
	private Color color;

}
