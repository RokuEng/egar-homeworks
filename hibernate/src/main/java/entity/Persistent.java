package entity;

import javax.persistence.NamedQuery;

public interface Persistent<ID> {
	ID getId();
}
