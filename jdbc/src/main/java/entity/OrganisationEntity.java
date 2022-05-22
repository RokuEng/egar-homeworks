package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class OrganisationEntity {
	private Integer id;
	private String email;
	private CustomerEntity customer;
}
