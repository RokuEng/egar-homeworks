package com.RokuEng.homeworks;

import com.RokuEng.homeworks.les2.domains.Event;
import com.RokuEng.homeworks.les2.domains.Trait;
import com.RokuEng.homeworks.les2.domains.entities.Message;
import com.RokuEng.homeworks.les2.domains.entities.Update;
import com.RokuEng.homeworks.les2.domains.entities.User;
import com.RokuEng.homeworks.les2.domains.events.ExecutableEvent;
import com.RokuEng.homeworks.les2.domains.events.SerializableEvent;
import com.RokuEng.homeworks.les2.domains.traits.Executable;
import com.RokuEng.homeworks.les2.domains.traits.Persistent;
import com.RokuEng.homeworks.les2.domains.traits.Serializable;

public class Application {

	public static void main(String[] args) {

		Event<Persistent> userEvent = new ExecutableEvent<>(
			new User(0, "RokuEng", Update.CREATED.name()),
			"Sends greeting message to CREATED user. . ."
		);

		Event<Persistent> messageEvent = new SerializableEvent<>(
			new Message(0, "First Message!")
		);

		Event<Persistent> anotherMessageEvent = new SerializableEvent<>(
			new Message(1, "Second Message!")
		);


		Event<Persistent>[] events = new Event[] {
			userEvent,
			messageEvent,
			anotherMessageEvent,
		};

		for (Event<Persistent> event : events) {
			System.out.println("Content  Object: " + event.getContent());

			if (event instanceof Serializable) {
				System.out.println(((Serializable) event).serialize());
			}

			if (event instanceof Trait) {
				System.out.println(((Trait) event).trait());
			}

			System.out.println("");
		}


	}

}
