package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class CoffeeEntity {
	private Integer id;
	private String name;
	private Short type;
	private List<ProviderEntity> provider = new LinkedList<>();
}
