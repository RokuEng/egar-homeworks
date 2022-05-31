package domain.program;

import java.math.BigDecimal;

public class BestProgram implements Program {

	private final BigDecimal incomingModifier = BigDecimal.valueOf(100);

	@Override
	public BigDecimal getIncomingModifier() {
		return incomingModifier;
	}
}
