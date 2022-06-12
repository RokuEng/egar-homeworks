package com.RokuEng.springdata;

import com.RokuEng.springdata.entity.client.Client;
import com.RokuEng.springdata.service.ClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("com.RokuEng.springdata.config");
		ClientService clientService = context.getBean(ClientService.class);

		Client clientFirst = new Client();
		Client clientSecond = new Client();

		clientFirst.setFirstname("John");
		clientFirst.setLastname("Kenn");

		clientSecond.setFirstname("Bob");
		clientSecond.setLastname("Atlas");

		clientService.save(clientFirst);
		clientService.save(clientSecond);
	}
}
