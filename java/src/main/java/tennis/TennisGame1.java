package tennis;

public class TennisGame1 implements TennisGame {

	private ScoreKeeper scoreKeeper;

	public TennisGame1(String player1Name, String player2Name) {
		scoreKeeper = new ScoreKeeper(player1Name, player2Name);
	}

	public void wonPoint(String playerName) {
		scoreKeeper.incrementScoreOf(playerName);
	}

	public String getScore() {
		return new ScoreTranslator(scoreKeeper).translate();
	}
}
