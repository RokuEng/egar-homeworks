package com.RokuEng.homeworks.les2.domains.events;

import com.RokuEng.homeworks.les2.domains.entities.Content;
import com.RokuEng.homeworks.les2.domains.traits.Executable;
import com.RokuEng.homeworks.les2.domains.traits.Serializable;
import lombok.Getter;
import lombok.Setter;

public class ExecutableEvent<T extends Content> extends SimpleEvent implements Executable, Serializable {

	@Getter
	@Setter
	private String executeString;

	@Override
	public String trait() {
		return execute();
	}

	@Override
	public String serialize() {
		return "ID: " + getContent().getId() + "\nContent: " + getContent().getContent() + "\nExecutable: " + executeString;
	}

	@Override
	public String execute() {
		return Executable.super.execute() + executeString;
	}

	public ExecutableEvent(Content content, String executeString) {
		super(content);
		this.executeString = executeString;
	}
}
