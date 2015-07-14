package tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisGame4 implements TennisGame {

	private static final String[] SCORE_STRINGS = { "Love", "Fifteen", "Thirty", "Forty" };
	private static final int FORTY = 3;
	private static final int THIRTY = 2;

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

	Object getCurrentPoints(String playerName) {
		String scoreString;
		if (playerNameToScoreStringMap.containsKey(playerName)) {
			scoreString = lookupScoreString(playerNameToScoreStringMap.get(playerName));
		} else {
			throw new PlayerNotFoundException(playerName + " is not part of this game.");
		}
		return scoreString;
	}

	private String lookupScoreString(Integer scoreStringIndex) {
		return (scoreStringIndex < SCORE_STRINGS.length) ? SCORE_STRINGS[scoreStringIndex] : "Ad";
	}

	/**
	 * 0  Love
	 * 1  Fifteen
	 * 2  Thirty
	 * 3  Forty
	 * 4  Game/Ad
	 * 
	 * p1 | p2 |p1+p2    | p1-p2   | state
	 * ===+====+=========+=========+========
	 * 0  | 0  | 0       | 0       | love-all
	 * 
	 * 1  | 0  | 1       | 1       | fifteen-love
	 * 0  | 1  | 1       | -1      | love-fifteen
	 * 
	 * 1  | 1  | 2       | 0       | fifteen-all
	 * 2  | 0  | 2       | 2       | thirty-love
	 * 0  | 2  | 2       | -2      | love-thirty
	 * 
	 * 0  | 3  | 3       | -3      | love-forty
	 * 3  | 0  | 3       | 3       | forty-love
	 * 2  | 1  | 3       | 1       | thirty-fifteen
	 * 1  | 2  | 3       | -1      | fifteen-thirty
	 * 
	 * 2  | 2  | 4       | 0       | thirty-all
	 * 3  | 1  | 4       | 2       | forty-fifteen
	 * 1  | 3  | 4       | -2      | fifteen-forty
	 * 4  | 0  | 4       | 4       | win for player 1
	 * 0  | 4  | 4       | -4      | win for player 2
	 * 
	 * 3  | 2  | 5       | 1       | forty-thirty
	 * 2  | 3  | 5       | -1      | thirty-forty
	 * 4  | 1  | 5       | 3       | win for player 1
	 * 1  | 4  | 5       | -3      | win for player 2
	 * 
	 * 3  | 3  | 6       | 0       | Deuce
	 * 4  | 2  | 6       | 2       | win for player 1
	 * 2  | 4  | 6       | -2      | win for player 2
	 * 
	 * 4  | 3  | 7       | 1       | advantage player 1
	 * 3  | 4  | 7       | -1      | advantage player 2
	 * 
	 * p1 == p2 && p1-p2 == 0      | Deuce
	 * p1 > p2 && p1-p2 == 1       | Advantage player1
	 * p1 < p2 && p1-p2 == -1      | Advantage player2
	 * p1 > p2 && p1-p2 > 1        | Win for player 1
	 * p2 < p1 && p1-p2 < -1       | Win for player 2
	 */
	private static final Map<Integer,Map<Integer,String>> SCORE_TABLE = new HashMap<Integer,Map<Integer,String>>() { {
		String[][] kk = { 
				{ "empty", "empty", "empty", "empty", "Love-All", "empty", "empty", "empty", "empty", },										//0 
				{ "empty", "empty", "empty", "Love-Fifteen", "empty", "Fifteen-Love", "empty", "empty", "empty", },								//1
				{ "empty", "empty", "Love-Thirty", "empty", "Fifteen-All", "empty", "Thirty-Love", "empty", "empty", },							//2
				{ "empty", "Love-Forty", "empty", "Fifteen-Thirty", "empty", "Thirty-Fifteen", "empty", "Forty-Love", "empty", },				//3
				{ "Win for player2", "empty", "Fifteen-Forty", "empty", "Thirty-All", "empty", "Forty-Fifteen", "empty", "Win for player1" },	//4
				{ "empty", "Win for player2", "empty", "Thirty-Forty", "empty", "Forty-Thirty", "empty", "Win for player1", "empty", },			//5
				{ "empty", "empty", "Win for player2", "empty", "Deuce", "empty", "Win for player1", "empty", "empty", },						//6
				{ "empty", "empty", "empty", "Advantage player2", "Deuce", "Advantage player1", "empty", "empty", "empty", },					//7
		};
		for(int sum=0; sum<8;sum++) {
			this.put(sum, new HashMap<Integer,String>());
			for(int idx=0;idx<kk[sum].length;idx++) {
				this.get(sum).put(idx-4, kk[sum][idx]);
			}
		}
	}
	};
	
	public String getScore() {
		String score;
		Integer p1s = playerNameToScoreStringMap.get(player1Key);
		Integer p2s = playerNameToScoreStringMap.get(player2Key);
		System.out.print(p1s + " " + p2s + " ");
		int sum = p1s+p2s;
		int diff = p1s-p2s;
		if ( sum > 7 ) {
			String[] dd =  { "Win for player2", "Advantage player2", "Deuce", "Advantage player1", "Win for player1" };
			score = dd[diff+2];
		} else {
			System.out.print(sum + " " + diff + " ");
			score = SCORE_TABLE.get(sum).get(diff);
			System.out.println(score);
		}
		return score;
//		String scoreString;
//		if (tied()) {
//			scoreString = getTieScore();
//		} else if (won()) {
//			scoreString = getWinScore(leader());
//		} else if (advantage()) {
//			scoreString = getAdScore(leader());
//		} else {
//			scoreString = getGameScore();
//		}
//		return scoreString;
	}

	private String getGameScore() {
		return getCurrentPoints(player1Key) + "-" + getCurrentPoints(player2Key);
	}

	private String getTieScore() {
		String scoreString;
		if (playerOneScore() > THIRTY) {
			scoreString = "Deuce";
		} else {
			scoreString = getCurrentPoints(player1Key) + "-All";
		}
		return scoreString;
	}

	private boolean advantage() {
		return playerOneScore() + playerTwoScore() > 5 && Math.abs(playerOneScore() - playerTwoScore()) == 1;
	}

	private boolean won() {
		return (playerOneScore() > FORTY || playerTwoScore() > FORTY)
				&& Math.abs(playerOneScore() - playerTwoScore()) > 1;
	}

	private boolean tied() {
		return playerOneScore() == playerTwoScore();
	}

	private String leader() {
		if (playerOneScore() > playerTwoScore()) {
			return "player1";
		}
		return "player2";
	}

	private String getWinScore(String playerId) {
		return "Win for " + playerId;
	}

	private String getAdScore(String playerId) {
		return "Advantage " + playerId;
	}

	private Integer playerTwoScore() {
		return playerNameToScoreStringMap.get(player2Key);
	}

	private Integer playerOneScore() {
		return playerNameToScoreStringMap.get(player1Key);
	}

}
