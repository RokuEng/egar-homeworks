package dao;

import entity.CustomerEntity;
import entity.OrganisationEntity;
import exception.CoffeeNotFoundException;
import util.QueryBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.DML.*;

public class OrganisationDao implements Dao<OrganisationEntity, Integer> {

	private static final String DELETE_COFFEE = QueryBuilder.build(DELETE, FROM, "Organisation", WHERE, PREPARED("id"));

	private static final String SELECT_ALL = QueryBuilder.build(
		SELECT, ALL, FROM, "organisation o",
		JOIN, "customer c", ON, "c.id = o.customer_id"
	);

	private static final String SAVE_ORGANISATION = QueryBuilder.build(INSERT, INTO, "Organisation(email, customer_id)", VALUES_START, "?,", "?", VALUES_END);
	private static final String UPDATE_ORGANISATION = QueryBuilder.build(UPDATE, "Organisation", SET, PREPARED_NEXT("email"), PREPARED("customer_id"), WHERE, PREPARED("id"));

	private static final String SAVE_CUSTOMER = QueryBuilder.build(INSERT, INTO, "Customer(name, phone, favorite_coffee_id)", VALUES_START, "?,", "?,", "?", VALUES_END);
	private static final String UPDATE_CUSTOMER = QueryBuilder.build(UPDATE, "Customer", SET, PREPARED_NEXT("name"), PREPARED_NEXT("phone"), PREPARED_NEXT("favorite_coffee_id"), WHERE, PREPARED("id"));

	@Override
	public List<OrganisationEntity> findAll() {
		try {
			return usePreparedStatement(SELECT_ALL, ps -> {
				ResultSet rs = ps.executeQuery();

				List<OrganisationEntity> entities = new LinkedList<>();

				while (rs.next()) {
					Integer organisationId = rs.getInt("id");
					String email = rs.getString("email");

					CustomerEntity customer = getCustomer(rs);
					entities.add(new OrganisationEntity(organisationId, email, customer));
				}

				return entities;
			});
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Optional<OrganisationEntity> findById(Integer integer) {
		try {
			return usePreparedStatement(SELECT_ALL, ps -> {
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					Integer organisationId = rs.getInt("id");
					String email = rs.getString("email");

					CustomerEntity customer = getCustomer(rs);
					OrganisationEntity organisationEntity = new OrganisationEntity(organisationId, email, customer);

					return Optional.of(organisationEntity);
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
	public void save(OrganisationEntity organisationEntity) {
		try {
			if (!findAll().stream().map(OrganisationEntity::getId).collect(Collectors.toList()).contains(organisationEntity.getId())) {

				usePreparedStatement(SAVE_ORGANISATION, ps -> {
					ps.setString(1, organisationEntity.getEmail());
					ps.setInt(2, organisationEntity.getCustomer().getId());
					ps.execute();
					return true;
				});

			} else {
				update(organisationEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(OrganisationEntity organisationEntity) {
		try {
			usePreparedStatement(UPDATE_ORGANISATION, ps -> {
				ps.setString(1, organisationEntity.getEmail());
				ps.setInt(2, organisationEntity.getCustomer().getId());
				ps.setInt(3, organisationEntity.getId());
				ps.execute();
				return true;
			});

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(OrganisationEntity organisationEntity) {
		try {
			usePreparedStatement(DELETE_COFFEE, ps -> {
				ps.setInt(1, organisationEntity.getId());
				ps.execute();
				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private CustomerEntity getCustomer(ResultSet rs) throws SQLException {
		Integer customerId = rs.getInt("customer_id");
		String customerName = rs.getString("name");
		String phone = rs.getString("phone");
		CustomerEntity customer = new CustomerEntity(customerId, customerName, phone,
			new CoffeeDao()
				.findById(rs.getInt("favorite_coffee_id"))
				.orElseThrow(CoffeeNotFoundException::new));
		return customer;
	}
}
