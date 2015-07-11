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
		if (playerOneScore() == playerTwoScore()) {
			if (playerOneScore() > 2) {
				scoreString = "Deuce";
			} else {
				scoreString = getCurrentPoints(player1Key) + "-All";
			}
		} else if (playerOneScore() > 3) {
			scoreString = getWinAdScore("player1", playerOneScore(), playerTwoScore());
		} else if (playerTwoScore() > 3) {
			scoreString = getWinAdScore("player2", playerTwoScore(), playerOneScore());
		} else {
			scoreString = getCurrentPoints(player1Key) + "-" + getCurrentPoints(player2Key);
		}
		return scoreString;
	}

	private String getWinAdScore(String playerId, int leader, int other) {
		String scoreString;
		int lead = leader - other;
		if (lead == 1) {
			scoreString = "Advantage " + playerId;
		} else if (lead > 1) {
			scoreString = "Win for " + playerId;
		} else {
			String otherPlayerId;
			if (playerId.contains("1")) {
				otherPlayerId = playerId.replace('1', '2');
			} else {
				otherPlayerId = playerId.replace('2', '1');
			}
			scoreString = getWinAdScore(otherPlayerId, other, leader);
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
