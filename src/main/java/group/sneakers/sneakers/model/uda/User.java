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

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role")
	private Rol rol; //Admin o cliente

}
