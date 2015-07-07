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
		String score = "";
		int tempScore = 0;
		if (getPlayer1Score() == getPlayer2Score()) {
			switch (getPlayer1Score()) {
			case 0:
				score = "Love-All";
				break;
			case 1:
				score = "Fifteen-All";
				break;
			case 2:
				score = "Thirty-All";
				break;
			default:
				score = "Deuce";
				break;

			}
		} else if (getPlayer1Score() >= 4 || getPlayer2Score() >= 4) {
			int minusResult = getPlayer1Score() - getPlayer2Score();
			if (minusResult == 1)
				score = "Advantage player1";
			else if (minusResult == -1)
				score = "Advantage player2";
			else if (minusResult >= 2)
				score = "Win for player1";
			else
				score = "Win for player2";
		} else {
			for (int i = 1; i < 3; i++) {
				if (i == 1)
					tempScore = getPlayer1Score();
				else {
					score += "-";
					tempScore = getPlayer2Score();
				}
				switch (tempScore) {
				case 0:
					score += "Love";
					break;
				case 1:
					score += "Fifteen";
					break;
				case 2:
					score += "Thirty";
					break;
				case 3:
					score += "Forty";
					break;
				}
			}
		}
		return score;
	}

	public int getPlayer1Score() {
		return scores.get(player1);
	}

	public int getPlayer2Score() {
		return scores.get(player2);
	}
}
