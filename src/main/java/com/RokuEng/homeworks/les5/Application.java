package com.RokuEng.homeworks.les5;

import com.RokuEng.homeworks.les2.domains.Event;
import com.RokuEng.homeworks.les2.domains.Trait;
import com.RokuEng.homeworks.les2.domains.entities.Message;
import com.RokuEng.homeworks.les2.domains.entities.Update;
import com.RokuEng.homeworks.les2.domains.events.ExecutableEvent;
import com.RokuEng.homeworks.les2.domains.events.SerializableEvent;
import com.RokuEng.homeworks.les2.domains.traits.Executable;
import com.RokuEng.homeworks.les2.domains.traits.Persistent;
import com.RokuEng.homeworks.les2.domains.traits.Serializable;
import com.RokuEng.homeworks.les5.domain.User;
import com.RokuEng.homeworks.les5.exception.UserNotInitializedException;
import lombok.extern.java.Log;

import java.util.Arrays;

public class Application {

	public static void main(String[] args) {

		User user = new User();
//		user.setId(0L);
//		user.setUsername("user");

		try {
			user.active();
		} catch (UserNotInitializedException e) {
			e.printStackTrace();
		}

	}

}
