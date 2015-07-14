package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisScoreTranslator {

	private static final Map<Integer, Map<Integer, String>> SCORE_TABLE = new HashMap<Integer, Map<Integer, String>>() {
		private static final long serialVersionUID = 1L;

		{
			String[][] basic_scores = { { null, null, null, null, "Love-All", null, null, null, null, },
					{ null, null, null, "Love-Fifteen", null, "Fifteen-Love", null, null, null, },
					{ null, null, "Love-Thirty", null, "Fifteen-All", null, "Thirty-Love", null, null, },
					{ null, "Love-Forty", null, "Fifteen-Thirty", null, "Thirty-Fifteen", null, "Forty-Love", null, },
					{ "Win for player2", null, "Fifteen-Forty", null, "Thirty-All", null, "Forty-Fifteen", null,
							"Win for player1" },
					{ null, "Win for player2", null, "Thirty-Forty", null, "Forty-Thirty", null, "Win for player1",
							null, },
					{ null, null, "Win for player2", null, "Deuce", null, "Win for player1", null, null, },
					{ null, null, null, "Advantage player2", "Deuce", "Advantage player1", null, null, null, }, };
			for (int sum = 0; sum < 8; sum++) {
				this.put(sum, new HashMap<Integer, String>());
				for (int idx = 0; idx < basic_scores[sum].length; idx++) {
					this.get(sum).put(idx - 4, basic_scores[sum][idx]);
				}
			}
		}
	};

	private static final String[] SCORES_BEYOND_DEUCE = { "Win for player2", "Advantage player2", "Deuce",
			"Advantage player1", "Win for player1" };
	
	public String translate(int player1score, int player2score) {
		String score;
		int sum = player1score+player2score;
		int diff = player1score-player2score;
		if (sum > 7) {
			score = SCORES_BEYOND_DEUCE[diff + 2];
		} else {
			score = SCORE_TABLE.get(sum).get(diff);
		}
		return score;
	}

}
