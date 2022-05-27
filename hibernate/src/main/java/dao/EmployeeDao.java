package dao;

import entity.employee.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeDao implements Dao<Employee, Integer> {
	@Override
	public Optional<Employee> findById(Integer integer) {
		return findById(Employee.class, integer);
	}

	@Override
	public List<Employee> findAll() {
		return findAll(Employee.class);
	}

	public Optional<Employee> findByIdWithPerson(Integer integer) {
		return findByEntityGraph(integer,Employee.class,eg -> eg.addAttributeNodes("person"));
	}
}
