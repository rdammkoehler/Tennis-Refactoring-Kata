package tennis;

public enum Points {
	ZERO, FIFTEEN, THIRTY, FORTY;

	public int intValue() {
		return ordinal();
	}
}
