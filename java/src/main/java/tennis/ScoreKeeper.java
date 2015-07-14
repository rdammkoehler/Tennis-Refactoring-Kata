package tennis;

public class ScoreKeeper {

	class ScoringException extends RuntimeException {
		private static final long serialVersionUID = 8990831494313911917L;

		public ScoringException(String msg) {
			super(msg);
		}
	}

	private int player1score, player2score;
	private String player1Name, player2Name;

	public ScoreKeeper(String player1, String player2) {
		player1Name = player1;
		player1score = 0;
		player2Name = player2;
		player2score = 0;
	}

	public void addPointTo(String playerName) {
		if (playerName.equals(player1Name)) {
			player1score++;
		} else if (playerName.equals(player2Name)) {
			player2score++;
		} else {
			throw new ScoringException("Only two players may score in the same game");
		}
	}

	public int getPlayer1Score() {
		return player1score;
	}

	public int getPlayer2Score() {
		return player2score;
	}

}
