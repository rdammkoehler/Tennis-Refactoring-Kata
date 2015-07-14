package tennis;

public class TennisGame4 implements TennisGame {

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

	private ScoreKeeper scoreKeeper;

	public TennisGame4(String player1, String player2) {
		validate(player1, player2);
		scoreKeeper = new ScoreKeeper(player1, player2);
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
		scoreKeeper.addPointTo(playerName);
	}

	public String getScore() {
		return new TennisScoreTranslator().translate(scoreKeeper.getPlayer1Score(), scoreKeeper.getPlayer2Score());
	}
}
