package domain.program;

import java.math.BigDecimal;

public class CommonProgram implements Program {
	private final BigDecimal incomingModifier = BigDecimal.valueOf(1.1d);

	@Override
	public BigDecimal getIncomingModifier() {
		return incomingModifier;
	}
}
