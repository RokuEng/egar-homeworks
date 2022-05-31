package domain.account;

import domain.program.Program;
import org.springframework.beans.factory.annotation.Autowired;
import util.Comparison;

import java.math.BigDecimal;

public class ClientAccount implements Account {

	private final Program program;

	private BigDecimal money = BigDecimal.ZERO;

	@Override
	public Program getProgram() {
		return program;
	}

	@Override
	public BigDecimal getMoney() {
		return money;
	}

	@Override
	public void addMoney(BigDecimal amount) {
		if (Comparison.biggerOrEqual(amount,BigDecimal.ZERO)) {
			money = money.add(amount);
		}
	}

	@Autowired
	public ClientAccount(Program program) {
		this.program = program;
	}
}
