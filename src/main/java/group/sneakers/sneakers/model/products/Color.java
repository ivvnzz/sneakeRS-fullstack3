package group.sneakers.sneakers.model.products;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Table(name = "colors")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;



}
