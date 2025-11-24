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

@Data
@Table(name = "ProductSales")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unitaryPrice")
    private Double unitaryPrice;

    @ManyToOne
    @JoinColumn(name = "sales")
    private Sales sales;

    @ManyToOne
    @JoinColumn(name = "product")
    private group.sneakers.sneakers.model.products.Product product;
}
