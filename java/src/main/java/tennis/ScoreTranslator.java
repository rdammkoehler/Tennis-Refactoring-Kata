package tennis;

import static tennis.Points.FORTY;
import static tennis.Points.THIRTY;

import java.util.HashMap;
import java.util.Map;

public class ScoreTranslator {

	public String translate(Points player1Score, Points player2Score) {
		String score = "";
		if (player1Score == player2Score) {
			if (player1Score.ordinal() > THIRTY.ordinal()) {
				score = "Deuce";
			} else {
				score = player1Score + "-All";
			}
		} else if (player1Score.ordinal() > FORTY.ordinal() || player2Score.ordinal() > FORTY.ordinal()) {
			Map<Integer,String> scores = new HashMap<Integer,String>() {
				private static final long serialVersionUID = 1L;
				{
					put(-1, "Advantage player2");
					put(0,"impossible");
					put(1, "Advantage player1");
				}
				@Override
				public String get(Object offset) {
					String score;
					if ( containsKey(offset)) {
						score = super.get(offset);
					} else {
						if ( ((Integer)offset).intValue() < 0 ) {
							score = "Win for player2";
						} else {
							score = "Win for player1";
						}
					}
					return score;
				}
			};
			int scoreOffset = player1Score.intValue() - player2Score.intValue();
			return scores.get(scoreOffset);
		} else {
			score = player1Score + "-" + player2Score;
		}
		return score;
	}

}
