import domain.client.BankClient;
import domain.client.Client;
import spring.Context;

import java.math.BigDecimal;

public class Application {
	public static void main(String[] args) {
		Client client = Context.client();
		client.getAccount().addMoney(BigDecimal.valueOf(1000));
		client.income();
		System.out.println(client.getAccount().getMoney());
	}
}
