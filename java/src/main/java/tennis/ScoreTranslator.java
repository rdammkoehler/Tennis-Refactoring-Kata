package tennis;

import static tennis.Points.FORTY;
import static tennis.Points.THIRTY;

import java.util.HashMap;

public class ScoreTranslator {

	private static final WinAdvTranslator WIN_ADV_TRANSLATOR = new WinAdvTranslator();
	private ScoreKeeper scoreKeeper;

	public ScoreTranslator(ScoreKeeper scoreKeeper) {
		this.scoreKeeper = scoreKeeper;
	}
	
	public String translate(ScoreKeeper scoreKeeper) {
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

	private Points getPlayer1Score() {
		return scoreKeeper.scoreOfPlayer1();
	}

	private Points getPlayer2Score() {
		return scoreKeeper.scoreOfPlayer2();
	}

	private boolean winOrAdvantage() {
		return getPlayer1Score().greaterThan(FORTY) || getPlayer2Score().greaterThan(FORTY);
	}

	private boolean tied() {
		return getPlayer1Score() == getPlayer2Score();
	}

	private String getWinningOrAdvantageScore() {
		return WIN_ADV_TRANSLATOR.translate(getScoreOffset());
	}

	private int getScoreOffset() {
		return getPlayer1Score().intValue() - getPlayer2Score().intValue();
	}

	private String getInGameScore() {
		String score;
		score = getPlayer1Score() + "-" + getPlayer2Score();
		return score;
	}

	private String getTieScore() {
		String score;
		if (getPlayer1Score().greaterThan(THIRTY)) {
			score = "Deuce";
		} else {
			score = getPlayer1Score() + "-All";
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
