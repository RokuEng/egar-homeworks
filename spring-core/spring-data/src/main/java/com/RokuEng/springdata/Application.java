package com.RokuEng.springdata;

import com.RokuEng.springdata.entity.account.Account;
import com.RokuEng.springdata.entity.client.Client;
import com.RokuEng.springdata.service.AccountService;
import com.RokuEng.springdata.service.ClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("com.RokuEng.springdata.config");
		ClientService clientService = context.getBean(ClientService.class);
		AccountService accountService = context.getBean(AccountService.class);

//		Client clientFirst = new Client();
//		clientFirst.setFirstname("John");
//		clientFirst.setLastname("Cool");
//
//		Client clientSecond = new Client();
//		clientSecond.setFirstname("Guy");
//		clientSecond.setLastname("Common");
//
//		clientService.save(clientFirst);
//		clientService.save(clientSecond);

		Client clientFirst = clientService.findById(57).orElseThrow();
		Client clientSecond = clientService.findById(58).orElseThrow();

		accountService.putOnDeposit(clientFirst.getAccounts().get(0), BigDecimal.valueOf(1000));

		System.out.println(clientFirst.getAccounts().get(0));
		System.out.println(clientSecond.getAccounts().get(0));

//		clientService.openCommonRUBAccount(clientFirst);
//		clientService.openCreditRUBAccount(clientSecond);
//
//		System.out.println(clientFirst);
//		System.out.println(clientSecond);
//
//		accountService.transferMoney(clientSecond.getAccounts().get(0),clientFirst.getAccounts().get(0),1000d);
//
//		System.out.println(clientFirst);
//		System.out.println(clientSecond);
//
		clientService.save(clientFirst);
		clientService.save(clientSecond);
	}
}
