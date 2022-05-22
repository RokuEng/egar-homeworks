package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProviderEntity {
	private Integer id;
	private String name;
	private String phone;
	private BigDecimal margin;
	private List<CoffeeEntity> providesCoffee;
}
