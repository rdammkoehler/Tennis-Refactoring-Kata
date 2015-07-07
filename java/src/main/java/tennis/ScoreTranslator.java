package tennis;

import static tennis.Points.FORTY;
import static tennis.Points.THIRTY;

import java.util.HashMap;

public class ScoreTranslator {

	private static final WinAdvTranslator WIN_ADV_TRANSLATOR = new WinAdvTranslator();
	private ScoreKeeper scoreKeeper;

	public String translate(ScoreKeeper scoreKeeper) {
		this.scoreKeeper = scoreKeeper;
		String score = "";
		if (tied()) {
			score = getTieScore();
		} else if (winOrAdvantage()) {
			score = getWinningOrAdvantageScore();
		} else {
			score = getInGameScore();
		}
		return score;
	}

	private boolean winOrAdvantage() {
		Points player1Score = scoreKeeper.scoreOfPlayer1();
		Points player2Score = scoreKeeper.scoreOfPlayer2();
		return player1Score.greaterThan(FORTY) || player2Score.greaterThan(FORTY);
	}

	private boolean tied() {
		Points player1Score = scoreKeeper.scoreOfPlayer1();
		Points player2Score = scoreKeeper.scoreOfPlayer2();
		return player1Score == player2Score;
	}

	private String getWinningOrAdvantageScore() {
		return WIN_ADV_TRANSLATOR.translate(getScoreOffset());
	}

	private int getScoreOffset() {
		Points player1Score = scoreKeeper.scoreOfPlayer1();
		Points player2Score = scoreKeeper.scoreOfPlayer2();
		return player1Score.intValue() - player2Score.intValue();
	}

	private String getInGameScore() {
		String score;
		Points player1Score = scoreKeeper.scoreOfPlayer1();
		Points player2Score = scoreKeeper.scoreOfPlayer2();
		score = player1Score + "-" + player2Score;
		return score;
	}

	private String getTieScore() {
		String score;
		Points player1Score = scoreKeeper.scoreOfPlayer1();
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
		if (super.containsKey(offset)) {
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
