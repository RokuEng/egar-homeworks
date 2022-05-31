package domain.account;

import domain.program.Program;

import java.math.BigDecimal;

public interface Account {
	Program getProgram();
	BigDecimal getMoney();
	void addMoney(BigDecimal amount);
}
