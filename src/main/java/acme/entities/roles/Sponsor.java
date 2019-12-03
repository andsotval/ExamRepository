
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsor extends UserRole {

	// Serialization identifier -----------------------

	private static final long serialVersionUID = 1L;

	//Attributes ---------------------------------------

	@NotBlank
	private String organizationName;

	//Derived attributes --------------------------------

	//Relationships -------------------------------------

}
