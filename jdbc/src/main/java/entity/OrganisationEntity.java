package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class OrganisationEntity {
	private String id;
	private String name;
	private String email;
	private CustomerEntity customer;
}
