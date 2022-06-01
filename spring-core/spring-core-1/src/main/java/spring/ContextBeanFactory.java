package spring;

import domain.client.Client;
import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.function.Function;

@UtilityClass
public class ContextBeanFactory {

	public Client client() {
		return useContext(context -> context.getBean("client",Client.class));
	}

	public <T> T useContext(Function<ApplicationContext, T> function) {
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml")) {
			return function.apply(context);
		}
	}
}
