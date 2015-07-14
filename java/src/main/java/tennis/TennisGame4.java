package tennis;

public class TennisGame4 implements TennisGame {

	class ScoringException extends RuntimeException {
		private static final long serialVersionUID = 8990831494313911917L;

		public ScoringException(String msg) {
			super(msg);
		}
	}

	class PlayerNotFoundException extends IllegalArgumentException {
		private static final long serialVersionUID = 8464992528900598154L;

		public PlayerNotFoundException(String msg) {
			super(msg);
		}
	}

	class GameConfigurationException extends IllegalArgumentException {
		private static final long serialVersionUID = 5030132112115654337L;

		public GameConfigurationException(String msg) {
			super(msg);
		}
	}

	private int player1score, player2score;
	private String player1Name, player2Name;

	public TennisGame4(String player1, String player2) {
		validate(player1, player2);
		player1Name = player1;
		player1score = 0;
		player2Name = player2;
		player2score = 0;
	}

	private void validate(String player1, String player2) {
		if (null == player1 || null == player2) {
			throw new GameConfigurationException("Players must have actual names");
		}
		if (player1.equals(player2)) {
			throw new GameConfigurationException("Players must be unique");
		}
	}

	public void wonPoint(String playerName) {
		if (playerName.equals(player1Name)) {
			player1score++;
		} else if (playerName.equals(player2Name)) {
			player2score++;
		} else {
			throw new ScoringException("Only two players may score in the same game");
		}
	}

	public String getScore() {
		return new TennisScoreTranslator().translate(player1score, player2score);
		
	}
}
