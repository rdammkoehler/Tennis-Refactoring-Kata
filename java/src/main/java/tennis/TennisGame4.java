package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisGame4 implements TennisGame {

	private Map<String, Integer> playerNameToScoreStringMap = new HashMap<String, Integer>();

	public void wonPoint(String playerName) {
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
			scoreString = "Love";
		}
		return scoreString;
	}

	private String lookupScoreString(Integer scoreStringIndex) {
		String[] scoreStrings = { "Love", "Fifteen", "Thirty", "Forty" };
		return scoreStrings[scoreStringIndex];
	}

}
