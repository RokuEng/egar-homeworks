package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerEntity {
	private Integer id;
	private String name;
	private String phone;
	private CoffeeEntity favoriteCoffee;
}
