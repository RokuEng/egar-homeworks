package dao;

import domain.person.Person;

import java.util.List;

public class PersonDao implements Dao<Person, Integer> {

	@Override
	public Person findById(Integer integer) {
		return findById(Person.class, integer);
	}

	@Override
	public List<Person> findAll() {
		return findAll(Person.class);
	}

	public List<Person> findByAge(Integer age) {
		return useCriteriaQuery(Person.class,(cb, query, root) -> query.where(
			cb.equal(root.get("age"),age)
		));
	}

	public List<Person> findByAgeGreater(Integer age) {
		return useCriteriaQuery(Person.class,(cb, query, root) -> query.where(
			cb.greaterThan(root.get("age"),age)
		));
	}
}
