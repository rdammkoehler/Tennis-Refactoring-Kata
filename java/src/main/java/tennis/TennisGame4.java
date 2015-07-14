package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisGame4 implements TennisGame {

	class ScoringException extends RuntimeException {
		private static final long serialVersionUID = 8990831494313911917L;

		public ScoringException(String msg) {
			super(msg);
		}
	}

	class PlayerNotFoundException extends IllegalArgumentException {
		private static final long serialVersionUID = 8464992528900598154L;

		public PlayerNotFoundException(String msg) {
			super(msg);
		}
	}

	class GameConfigurationException extends IllegalArgumentException {
		private static final long serialVersionUID = 5030132112115654337L;

		public GameConfigurationException(String msg) {
			super(msg);
		}
	}

	private static final Map<Integer,Map<Integer,String>> SCORE_TABLE = new HashMap<Integer,Map<Integer,String>>() {
		private static final long serialVersionUID = 1L;
		{
			String[][] basic_scores = { 
					{ null, null, null, null, "Love-All", null, null, null, null, },
					{ null, null, null, "Love-Fifteen", null, "Fifteen-Love", null, null, null, },
					{ null, null, "Love-Thirty", null, "Fifteen-All", null, "Thirty-Love", null, null, },
					{ null, "Love-Forty", null, "Fifteen-Thirty", null, "Thirty-Fifteen", null, "Forty-Love", null, },
					{ "Win for player2", null, "Fifteen-Forty", null, "Thirty-All", null, "Forty-Fifteen", null, "Win for player1" },
					{ null, "Win for player2", null, "Thirty-Forty", null, "Forty-Thirty", null, "Win for player1", null, },
					{ null, null, "Win for player2", null, "Deuce", null, "Win for player1", null, null, },
					{ null, null, null, "Advantage player2", "Deuce", "Advantage player1", null, null, null, },
			};
			for(int sum=0; sum<8;sum++) {
				this.put(sum, new HashMap<Integer,String>());
				for(int idx=0;idx<basic_scores[sum].length;idx++) {
					this.get(sum).put(idx-4, basic_scores[sum][idx]);
				}
			}
		}
	};
	
	private static final String[] SCORES_BEYOND_DEUCE =  { "Win for player2", "Advantage player2", "Deuce", "Advantage player1", "Win for player1" };
	
	private Map<String, Integer> playerNameToScoreStringMap = new HashMap<String, Integer>();
	private String player1Key, player2Key;

	public TennisGame4(String player1, String player2) {
		validate(player1, player2);
		playerNameToScoreStringMap.put(player1, 0);
		playerNameToScoreStringMap.put(player2, 0);
		player1Key = player1;
		player2Key = player2;
	}

	private void validate(String player1, String player2) {
		if (null == player1 || null == player2) {
			throw new GameConfigurationException("Players must have actual names");
		}
		if (player1.equals(player2)) {
			throw new GameConfigurationException("Players must be unique");
		}
	}

	public void wonPoint(String playerName) {
		if (!playerNameToScoreStringMap.containsKey(playerName)) {
			throw new ScoringException("Only two players may score in the same game");
		} else {
			int currentScoreStringIndex = playerNameToScoreStringMap.get(playerName);
			int newScoreStringIndex = currentScoreStringIndex + 1;
			playerNameToScoreStringMap.put(playerName, newScoreStringIndex);
		}
	}

	
	public String getScore() {
		String score;
		int sum = getSumOfPlayerScores();
		int diff = getDifferenceOfPlayerScores();
		if ( sum > 7 ) {
			score = SCORES_BEYOND_DEUCE[diff+2];
		} else {
			score = SCORE_TABLE.get(sum).get(diff);
		}
		return score;
	}

	private int getDifferenceOfPlayerScores() {
		int diff = playerNameToScoreStringMap.get(player1Key)-playerNameToScoreStringMap.get(player2Key);
		return diff;
	}

	private int getSumOfPlayerScores() {
		int sum = playerNameToScoreStringMap.get(player1Key)+playerNameToScoreStringMap.get(player2Key);
		return sum;
	}
}
