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
			String[] scores = { "Win for player2", "Win for player2", "Win for player2", "Advantage player2", "impossible", "Advantage player1", "Win for player1", "Win for player1", "Win for player1" };
			int scoreOffset = player1Score.intValue() - player2Score.intValue();
			return scores[(scoreOffset+4)];
		} else {
			score = player1Score + "-" + player2Score;
		}
		return score;
	}

}
