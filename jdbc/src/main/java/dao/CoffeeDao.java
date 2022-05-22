package dao;

import entity.CoffeeEntity;
import entity.ProviderEntity;
import util.QueryBuilder;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.DML.*;

public class CoffeeDao implements Dao<CoffeeEntity, Integer> {
	private static final String SELECT_ALL = QueryBuilder.build(SELECT, ALL, FROM, "coffee");

	private static final String SELECT_BY_ID = QueryBuilder.build(SELECT, ALL, FROM, "coffee", WHERE, PREPARED("id"));

	private static final String SELECT_PROVIDERS_BY_COFFEE_ID = QueryBuilder.build(
		SELECT, ALL, FROM, "Provider_Coffee pc",
		JOIN, "Provider p", ON, "p.id = pc.provider_id",
		WHERE, PREPARED("coffee_id")
	);

	private static final String DELETE_COFFEE = QueryBuilder.build(DELETE, FROM, "Coffee", WHERE, PREPARED("id"));

	private static final String SELECT_COFFEE_BY_COFFEE_ID = QueryBuilder.build(
		SELECT, ALL, FROM, "Provider_Coffee pc",
		JOIN, "Coffee c", ON, "c.id = pc.coffee_id",
		WHERE, PREPARED("coffee_id")
	);

	private static final String SAVE_COFFEE = QueryBuilder.build(INSERT, INTO, "Coffee(name, type)", VALUES_START, "?,", "?", VALUES_END);
	private static final String UPDATE_COFFEE = QueryBuilder.build(UPDATE, "Coffee", SET, PREPARED_NEXT("name"), PREPARED("type"), WHERE, PREPARED("id"));

	@Override
	public List<CoffeeEntity> findAll() {
		try {
			return usePreparedStatement(SELECT_ALL,ps -> {

				ResultSet rs = ps.executeQuery();

				List<CoffeeEntity> entities = new LinkedList<>();

				while (rs.next()) {
					Integer id = rs.getInt("id");
					String name = rs.getString("name");
					Short type = rs.getShort("type");

					entities.add(new CoffeeEntity(id, name, type, findAllProvidersByCoffeeId(id)));
				}

				return entities;
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Optional<CoffeeEntity> findById(Integer id) {
		try {
			return usePreparedStatement(SELECT_BY_ID,ps -> {
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					CoffeeEntity coffeeEntity = new CoffeeEntity(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getShort("type"),
						findAllProvidersByCoffeeId(rs.getInt("id"))
					);
					return Optional.of(coffeeEntity);
				} else {
					return Optional.empty();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(CoffeeEntity coffeeEntity) {
		try {
			if (!findAll().stream().map(CoffeeEntity::getId).collect(Collectors.toList()).contains(coffeeEntity.getId())) {

				System.out.println(SAVE_COFFEE);

				usePreparedStatement(SAVE_COFFEE, ps -> {
					ps.setString(1,coffeeEntity.getName());
					ps.setShort(2,coffeeEntity.getType());
					ps.execute();
					return true;
				});
			} else {
				update(coffeeEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(CoffeeEntity coffeeEntity) {
		try {

			System.out.println(UPDATE_COFFEE);

			usePreparedStatement(UPDATE_COFFEE, ps -> {
				ps.setString(1,coffeeEntity.getName());
				ps.setShort(2,coffeeEntity.getType());
				ps.setInt(3,coffeeEntity.getId());
				ps.execute();
				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(CoffeeEntity coffeeEntity) {
		try {
			usePreparedStatement(DELETE_COFFEE,ps -> {
				ps.setInt(1,coffeeEntity.getId());
				ps.execute();
				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private List<ProviderEntity> findAllProvidersByCoffeeId(Integer coffee_id) {
		try {
			return usePreparedStatement(SELECT_PROVIDERS_BY_COFFEE_ID,ps -> {
				ps.setInt(1, coffee_id);
				ResultSet rs = ps.executeQuery();

				List<ProviderEntity> providers = new LinkedList<>();

				while (rs.next()) {
					Integer id = rs.getInt("id");
					String name = rs.getString("name");
					String phone = rs.getString("phone");
					BigDecimal margin = rs.getBigDecimal("margin");

					providers.add(new ProviderEntity(id,name,phone,margin, findAllCoffeeByProviderId(coffee_id)));
				}

				return providers;
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<CoffeeEntity> findAllCoffeeByProviderId(Integer coffee_id) {
		try {
			return usePreparedStatement(SELECT_COFFEE_BY_COFFEE_ID, ps -> {
				ps.setInt(1, coffee_id);
				ResultSet rs = ps.executeQuery();

				List<CoffeeEntity> coffee = new LinkedList<>();

				while (rs.next()) {
					Integer id = rs.getInt("id");
					String name = rs.getString("name");
					Short type = rs.getShort("type");

					coffee.add(new CoffeeEntity(id, name,type,null));
				}

				return coffee;
			});
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
