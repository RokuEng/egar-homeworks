package dao;

import entity.employee.Employee;

import java.util.List;

public class EmployeeDao implements Dao<Employee, Integer> {
	@Override
	public Employee findById(Integer integer) {
		return findById(Employee.class, integer);
	}

	@Override
	public List<Employee> findAll() {
		return findAll(Employee.class);
	}
}
