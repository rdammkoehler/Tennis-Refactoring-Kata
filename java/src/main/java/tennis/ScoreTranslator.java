package tennis;

import static tennis.Points.FORTY;

public class ScoreTranslator {

	public String translate(Points player1Score, Points player2Score) {
		String score = "";
		if (player1Score == player2Score) {
			if (player1Score.ordinal() >= FORTY.ordinal()) {
				score = "Deuce";
			} else {
				score = player1Score + "-All";
			}
		} else if (player1Score.ordinal() > FORTY.ordinal() || player2Score.ordinal() > FORTY.ordinal()) {
			int minusResult = player1Score.intValue() - player2Score.intValue();
			if (minusResult == 1)
				score = "Advantage player1";
			else if (minusResult == -1)
				score = "Advantage player2";
			else if (minusResult >= 2)
				score = "Win for player1";
			else
				score = "Win for player2";
		} else {
			score = player1Score + "-" + player2Score;
		}
		return score;
	}

}
