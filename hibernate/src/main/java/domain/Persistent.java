package domain;

import javax.persistence.CascadeType;

public interface Persistent<ID> {
	ID getId();
}
