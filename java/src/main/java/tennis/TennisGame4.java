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
		String[] scoreStrings = { "Love", "Fifteen", "Thirty", "Forty" };
		return (scoreStringIndex < scoreStrings.length) ? scoreStrings[scoreStringIndex] : "Ad";
	}

	public String getScore() {
		String scoreString;
		if (playerOneScore() > 3 && (playerOneScore() - playerTwoScore()) > 1) {
			scoreString = "Win for player1";
		} else if (playerTwoScore() > 3 && (playerTwoScore() - playerOneScore()) > 1) {
			scoreString = "Win for player2";
		} else if (playerOneScore() > 3 && (playerOneScore() - playerTwoScore()) == 1) {
			scoreString = "Advantage player1";
		} else if (playerTwoScore() > 3 && (playerTwoScore() - playerOneScore()) == 1) {
			scoreString = "Advantage player2";
		} else {
			if (playerOneScore() == playerTwoScore()) {
				if (playerOneScore() > 2) {
					scoreString = "Deuce";
				} else {
					scoreString = getCurrentPoints(player1Key) + "-All";
				}
			} else {
				scoreString = getCurrentPoints(player1Key) + "-" + getCurrentPoints(player2Key);
			}
		}
		return scoreString;
	}

	private Integer playerTwoScore() {
		return playerNameToScoreStringMap.get(player2Key);
	}

	private Integer playerOneScore() {
		return playerNameToScoreStringMap.get(player1Key);
	}

}
