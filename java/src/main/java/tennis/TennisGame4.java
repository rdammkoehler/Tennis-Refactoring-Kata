package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisGame4 implements TennisGame {

	private Map<String, String> playerNameToScoreStringMap = new HashMap<String, String>();

	public void wonPoint(String playerName) {
		playerNameToScoreStringMap.put(playerName, "");
	}

	public String getScore() {
		return null;
	}

	Object getCurrentPoints(String playerName) {
		String scoreString = "Fifteen";
		if (playerNameToScoreStringMap.containsKey(playerName)) {

		} else {
			scoreString = "Love";
		}
		return scoreString;
	}

}
