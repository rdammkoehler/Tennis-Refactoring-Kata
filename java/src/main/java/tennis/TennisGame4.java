package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisGame4 implements TennisGame {

	class ScoringException extends RuntimeException {
		private static final long serialVersionUID = 8990831494313911917L;
		public ScoringException(String msg) { super(msg); }
	}
	
	class PlayerNotFoundException extends IllegalArgumentException {
		public PlayerNotFoundException(String msg) { super(msg); }

		private static final long serialVersionUID = 8464992528900598154L;
	}
	
	private Map<String, Integer> playerNameToScoreStringMap = new HashMap<String, Integer>();

	public TennisGame4(String player1, String player2) {
		playerNameToScoreStringMap.put(player1, 0);
		playerNameToScoreStringMap.put(player2, 0);
	}

	public void wonPoint(String playerName) {
		if (playerNameToScoreStringMap.size() == 2 && !playerNameToScoreStringMap.containsKey(playerName)) {
			throw new ScoringException("Only two players may score in the same game");
		}
		if (playerNameToScoreStringMap.containsKey(playerName)) {
			int currentScoreStringIndex = playerNameToScoreStringMap.get(playerName);
			int newScoreStringIndex = currentScoreStringIndex + 1;
			playerNameToScoreStringMap.put(playerName, newScoreStringIndex);
		} else {
			playerNameToScoreStringMap.put(playerName, 1);
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
