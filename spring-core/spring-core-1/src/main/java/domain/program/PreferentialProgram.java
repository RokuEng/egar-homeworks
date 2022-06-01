package domain.program;

import java.math.BigDecimal;

public class PreferentialProgram implements Program {

	private final BigDecimal incomingModifier = BigDecimal.valueOf(1.35d);
	@Override
	public BigDecimal getIncomingModifier() {
		return incomingModifier;
	}
}
