package tennis;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TennisTest {

	private int player1Score;
	private int player2Score;
	private String expectedScore;
	private String condition;

	public TennisTest(int player1Score, int player2Score, String expectedScore, String condition) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.expectedScore = expectedScore;
		this.condition = condition;
	}

	@Parameters
	public static Collection<Object[]> getAllScores() {
		return Arrays.asList(new Object[][] { 
				{ 0, 0, "Love-All", "Tied at 0" }, //
				{ 1, 1, "Fifteen-All", "Tied at 15" }, //
				{ 2, 2, "Thirty-All", "Tied at 30" }, //
				{ 3, 3, "Deuce", "Tied at 40" }, //
				{ 4, 4, "Deuce", "Tied at 40+1" }, //

				{ 1, 0, "Fifteen-Love", "15-Love" }, //
				{ 0, 1, "Love-Fifteen", "Love-15" }, //
				{ 2, 0, "Thirty-Love", "30-Love" }, //
				{ 0, 2, "Love-Thirty", "Love-30" }, //
				{ 3, 0, "Forty-Love", "40-Love" }, //
				{ 0, 3, "Love-Forty", "Love-40" }, //
				{ 4, 0, "Win for player1", "Player1 Wins" }, //
				{ 0, 4, "Win for player2", "Player2 Wins" }, //

				{ 2, 1, "Thirty-Fifteen", "30-15" }, //
				{ 1, 2, "Fifteen-Thirty", "15-30" }, //
				{ 3, 1, "Forty-Fifteen", "40-15" }, //
				{ 1, 3, "Fifteen-Forty", "15-40" }, //
				{ 4, 1, "Win for player1", "Player 1 Wins" }, //
				{ 1, 4, "Win for player2", "Player 2 Wins" }, //

				{ 3, 2, "Forty-Thirty", "40-30" }, //
				{ 2, 3, "Thirty-Forty", "30-40" }, //
				{ 4, 2, "Win for player1", "Player 1 Wins" }, //
				{ 2, 4, "Win for player2", "Player 2 Wins" }, //

				{ 4, 3, "Advantage player1", "Player 1 has advantage 4:3" }, //
				{ 3, 4, "Advantage player2", "Player 2 has advantage 3:4" }, //
				{ 5, 4, "Advantage player1", "Player 1 has advantage 5:4" }, //
				{ 4, 5, "Advantage player2", "Player 2 has advantage 4:5" }, //
				{ 15, 14, "Advantage player1", "Player 1 has advantage 15:14" }, //
				{ 14, 15, "Advantage player2", "Player 2 has advantage 14:15" }, //

				{ 6, 4, "Win for player1", "Player 1 wins 6:4" }, //
				{ 4, 6, "Win for player2", "Player 2 wins 4:6" }, //
				{ 16, 14, "Win for player1", "Player 1 wins 16:14" }, //
				{ 14, 16, "Win for player2", "Player 2 wins 14:16" }, //
		});
	}

	public void checkAllScores(TennisGame game, String player1, String player2) {
		int highestScore = Math.max(this.player1Score, this.player2Score);
		for (int i = 0; i < highestScore; i++) {
			if (i < this.player1Score)
				game.wonPoint(player1);
			if (i < this.player2Score)
				game.wonPoint(player2);
		}
		assertEquals(this.condition, this.expectedScore, game.getScore());
	}

	@Test
	public void checkAllScoresTennisGame1() {
		TennisGame1 game = new TennisGame1("player1", "player2");
		checkAllScores(game, "player1", "player2");
	}

	@Test
	public void checkAllScoresTennisGame2() {
		TennisGame2 game = new TennisGame2("player1", "player2");
		checkAllScores(game, "player1", "player2");
	}

	@Test
	public void checkAllScoresTennisGame3() {
		TennisGame3 game = new TennisGame3("player1", "player2");
		checkAllScores(game, "player1", "player2");
	}

	@Test
	public void checkAllScoresTennisGame4() {
		checkAllScores(new TennisGame4("Andre Agassi", "John McEnroe"), "Andre Agassi", "John McEnroe");
	}
}
