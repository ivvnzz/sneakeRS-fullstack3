package group.sneakers.sneakers.model.sales;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@Table(name = "sales")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date")
	private Date date;

	@Column(name = "total")
	private Double total;

	@ManyToOne
	// Use quoted column name "user" to match existing database column without renaming it.
	// This avoids requiring a DB migration when the column is called `user` (a reserved word).
	@JoinColumn(name = "\"user\"")
	private group.sneakers.sneakers.model.uda.User user;

	@ManyToOne
	@JoinColumn(name = "status")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "payment_metod")
	private PaymentMetod paymentMetod;

	@ManyToOne
	@JoinColumn(name = "shipping_metod")
	private ShippingMetod shippingMetod;

}
