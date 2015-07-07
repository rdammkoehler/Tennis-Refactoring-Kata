package tennis;

public class TennisGame1 implements TennisGame {

	private int player1Score = 0;
	private int player2Score = 0;

	public TennisGame1(String player1Name, String player2Name) {
		if ( player1Name == null ) {
			throw new IllegalArgumentException("Player One must have a proper name");
		}
		if ( player2Name == null ) {
			throw new IllegalArgumentException("Player Two must have a proper name");
		}
		if ( player1Name.equals(player2Name) ) {
			throw new IllegalArgumentException("Player names must be unique");
		}
	}

	public void wonPoint(String playerName) {
		if (playerName == "player1")
			player1Score += 1;
		else
			player2Score += 1;
	}

	public String getScore() {
		String score = "";
		int tempScore = 0;
		if (player1Score == player2Score) {
			switch (player1Score) {
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
		} else if (player1Score >= 4 || player2Score >= 4) {
			int minusResult = player1Score - player2Score;
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
					tempScore = player1Score;
				else {
					score += "-";
					tempScore = player2Score;
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
}
