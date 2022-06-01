import domain.client.Client;
import spring.ContextBeanFactory;

import java.math.BigDecimal;

public class Application {
	public static void main(String[] args) {
		Client client = ContextBeanFactory.client();

		client.getAccount().addMoney(BigDecimal.valueOf(1000));
		client.income();

		System.out.println(client.getAccount().getMoney());
	}
}
