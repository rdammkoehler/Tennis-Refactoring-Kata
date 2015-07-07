package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {

	private Map<String, Integer> scores = new HashMap<String, Integer>();
	private String player1, player2;

	public TennisGame1(String player1Name, String player2Name) {
		if (player1Name == null) {
			throw new IllegalArgumentException("Player One must have a proper name");
		}
		if (player2Name == null) {
			throw new IllegalArgumentException("Player Two must have a proper name");
		}
		if (player1Name.equals(player2Name)) {
			throw new IllegalArgumentException("Player names must be unique");
		}
		player1 = player1Name;
		scores.put(player1Name, 0);
		player2 = player2Name;
		scores.put(player2Name, 0);
	}

	public void wonPoint(String playerName) {
		if (scores.containsKey(playerName)) {
			scores.put(playerName, scores.get(playerName) + 1);
		} else {
			throw new IllegalArgumentException(playerName + " is not part of this game");
		}
	}

	public String getScore() {
		return new ScoreTranslator().translate(scores.get(player1), scores.get(player2));
	}
}
