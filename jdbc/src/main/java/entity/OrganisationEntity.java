package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganisationEntity {
	private Integer id;
	private String email;
	private CustomerEntity customer;
}
