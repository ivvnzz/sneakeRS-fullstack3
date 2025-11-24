package group.sneakers.sneakers.model.uda;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "street")
	private String street;

	@Column(name = "number")
	private String number;

	@Column(name = "zip_code")
	private String zipCode;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "commune")
	private Commune commune;
}
