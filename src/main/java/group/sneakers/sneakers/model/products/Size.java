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
@Table(name = "sizes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private Double number;

    @Column(name = "description")
    private String description;

}
