package tennis;

public enum Points {
	ZERO, FIFTEEN, THIRTY, FORTY, ADD1, ADD2, ADD3, ADD4, ADD5, ADD6, ADD7, ADD8, ADD9, ADD10, ADD11, ADD12, ADD13;

	public int intValue() {
		return ordinal();
	}
	
	public Points next() {
		return Points.values()[intValue()+1];
	}
}
