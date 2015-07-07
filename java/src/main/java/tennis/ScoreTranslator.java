package tennis;

import static tennis.Points.FORTY;
import static tennis.Points.THIRTY;

import java.util.HashMap;

public class ScoreTranslator {

	private static final WinAdvTranslator WIN_ADV_TRANSLATOR = new WinAdvTranslator();

	public String translate(Points player1Score, Points player2Score) {
		String score = "";
		if (player1Score == player2Score) {
			score = getTieScore(player1Score);
		} else if (player1Score.greaterThan(FORTY) || player2Score.greaterThan(FORTY)) {
			score = getWinningOrAdvantageScore(player1Score, player2Score);
		} else {
			score = getInGameScore(player1Score, player2Score);
		}
		return score;
	}

	private String getWinningOrAdvantageScore(Points player1Score, Points player2Score) {
		return WIN_ADV_TRANSLATOR.translate(getScoreOffset(player1Score, player2Score));
	}

	private int getScoreOffset(Points player1Score, Points player2Score) {
		return player1Score.intValue() - player2Score.intValue();
	}

	private String getInGameScore(Points player1Score, Points player2Score) {
		String score;
		score = player1Score + "-" + player2Score;
		return score;
	}

	private String getTieScore(Points player1Score) {
		String score;
		if (player1Score.greaterThan(THIRTY)) {
			score = "Deuce";
		} else {
			score = player1Score + "-All";
		}
		return score;
	}

}

class WinAdvTranslator extends HashMap<Integer, String> {
	private static final long serialVersionUID = 1L;

	{
		put(-1, "Advantage player2");
		put(0, "impossible");
		put(1, "Advantage player1");
	}

	public String translate(Integer offset) {
		String score;
		if (containsKey(offset)) {
			score = super.get(offset);
		} else {
			if (offset < 0) {
				score = "Win for player2";
			} else {
				score = "Win for player1";
			}
		}
		return score;
	}

}
