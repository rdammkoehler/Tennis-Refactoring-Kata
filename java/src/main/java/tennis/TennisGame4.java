package tennis;

import java.util.HashMap;
import java.util.Map;

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

	private Map<String, Integer> playerNameToScoreStringMap = new HashMap<String, Integer>();

	public TennisGame4(String player1, String player2) {
		if (null == player1 || null == player2) {
			throw new GameConfigurationException("Players must have actual names");
		}
		if (player1.equals(player2)) {
			throw new GameConfigurationException("Players must be unique");
		}
		playerNameToScoreStringMap.put(player1, 0);
		playerNameToScoreStringMap.put(player2, 0);
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

	public String getScore() {
		return null;
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
		String[] scoreStrings = { "Love", "Fifteen", "Thirty", "Forty" };
		return scoreStrings[scoreStringIndex];
	}

}
