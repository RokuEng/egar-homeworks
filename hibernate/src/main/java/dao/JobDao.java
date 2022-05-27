package dao;

import entity.job.Job;

import java.util.List;
import java.util.Optional;

public class JobDao implements Dao<Job, Integer> {
	@Override
	public Optional<Job> findById(Integer integer) {
		return findById(Job.class, integer);
	}

	@Override
	public List<Job> findAll() {
		return findAll(Job.class);
	}
}
