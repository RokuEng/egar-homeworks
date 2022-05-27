package dao;

import entity.employee.State;

import java.util.List;
import java.util.Optional;

public class StateDao implements Dao<State, Integer> {
	@Override
	public Optional<State> findById(Integer integer) {
		return findById(State.class, integer);
	}

	@Override
	public List<State> findAll() {
		return findAll(State.class);
	}

	public Optional<State> findByIdWithEmployees(Integer id) {
		return findByEntityGraph(id, State.class, eg -> {
			eg.addAttributeNodes("employees");
		});
	}
}
