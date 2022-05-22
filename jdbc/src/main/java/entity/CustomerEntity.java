package entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerEntity {
	private Integer id;
	private String name;
	private String phone;
	private CoffeeEntity favoriteCoffee;
}
