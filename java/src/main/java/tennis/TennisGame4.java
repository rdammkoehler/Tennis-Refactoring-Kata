package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisGame4 implements TennisGame {

	private static final String[] SCORE_STRINGS = { "Love", "Fifteen", "Thirty", "Forty" };
	private static final int FORTY = 3;
	private static final int THIRTY = 2;

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

	private Map<String, Integer> playerNameToScoreStringMap = new HashMap<String, Integer>();
	private String player1Key, player2Key;

	public TennisGame4(String player1, String player2) {
		validate(player1, player2);
		playerNameToScoreStringMap.put(player1, 0);
		playerNameToScoreStringMap.put(player2, 0);
		player1Key = player1;
		player2Key = player2;
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
		if (!playerNameToScoreStringMap.containsKey(playerName)) {
			throw new ScoringException("Only two players may score in the same game");
		} else {
			int currentScoreStringIndex = playerNameToScoreStringMap.get(playerName);
			int newScoreStringIndex = currentScoreStringIndex + 1;
			playerNameToScoreStringMap.put(playerName, newScoreStringIndex);
		}
	}

	Object getCurrentPoints(String playerName) {
		String scoreString;
		if (playerNameToScoreStringMap.containsKey(playerName)) {
			scoreString = lookupScoreString(playerNameToScoreStringMap.get(playerName));
		} else {
			throw new PlayerNotFoundException(playerName + " is not part of this game.");
		}
		return scoreString;
	}

	private String lookupScoreString(Integer scoreStringIndex) {
		return (scoreStringIndex < SCORE_STRINGS.length) ? SCORE_STRINGS[scoreStringIndex] : "Ad";
	}

	/**
	 * p1+p2   | p1-p2   | state
	 * ========+=========+========
	 * 0       | 0       | love-all
	 * 1       | 1       | fifteen-love
	 * 1       | -1      | love-fifteen
	 * 2       | 0       | fifteen-all
	 * 2       | 2       | thirty-love
	 * 2       | -2      | love-thirty
	 * 
	 * 3       | 0       | forty-love
	 * 3       | 1       | thirty-fifteen
	 * 3       | -1      | fifteen-thirty
	 * 
	 * 4       | 0       | thirty-all
	 * 4       | 1       | thirty-fifteen
	 * 4       | -1      | fifteen-thirty
	 * 4       | 2       | thirty-love
	 * 4       | -2      | love-thirty
	 * 
	 * 5       | 1       | Win for player1
	 * 5       | -5
	 * 
	 * 6       | 0       | forty-all
	 * 
	 * 7
	 * 
	 * 8       | 0       | deuce
	 */
	public String getScore() {
		String scoreString;
		if (tied()) {
			scoreString = getTieScore();
		} else if (won()) {
			scoreString = getWinScore(leader());
		} else if (advantage()) {
			scoreString = getAdScore(leader());
		} else {
			scoreString = getGameScore();
		}
		return scoreString;
	}

	private String getGameScore() {
		return getCurrentPoints(player1Key) + "-" + getCurrentPoints(player2Key);
	}

	private String getTieScore() {
		String scoreString;
		if (playerOneScore() > THIRTY) {
			scoreString = "Deuce";
		} else {
			scoreString = getCurrentPoints(player1Key) + "-All";
		}
		return scoreString;
	}

	private boolean advantage() {
		return playerOneScore() + playerTwoScore() > 5 && Math.abs(playerOneScore() - playerTwoScore()) == 1;
	}

	private boolean won() {
		return (playerOneScore() > FORTY || playerTwoScore() > FORTY)
				&& Math.abs(playerOneScore() - playerTwoScore()) > 1;
	}

	private boolean tied() {
		return playerOneScore() == playerTwoScore();
	}

	private String leader() {
		if (playerOneScore() > playerTwoScore()) {
			return "player1";
		}
		return "player2";
	}

	private String getWinScore(String playerId) {
		return "Win for " + playerId;
	}

	private String getAdScore(String playerId) {
		return "Advantage " + playerId;
	}

	private Integer playerTwoScore() {
		return playerNameToScoreStringMap.get(player2Key);
	}

	private Integer playerOneScore() {
		return playerNameToScoreStringMap.get(player1Key);
	}

}
