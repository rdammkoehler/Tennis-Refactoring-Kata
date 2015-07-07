package tennis;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tennis.TennisGame;
import tennis.TennisGame1;
import tennis.TennisGame2;
import tennis.TennisGame3;

@RunWith(Parameterized.class)
public class TennisTest {

	private int player1Score;
	private int player2Score;
	private String expectedScore;

	public TennisTest(int player1Score, int player2Score, String expectedScore) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.expectedScore = expectedScore;
	}

	@Parameters
	public static Collection<Object[]> getAllScores() {
		return Arrays.asList(new Object[][] { 
				{ 0, 0, "Love-All" },			//0 
				{ 1, 1, "Fifteen-All" }, 		//1
				{ 2, 2, "Thirty-All" },			//2
				{ 3, 3, "Deuce" }, 				//3
				{ 4, 4, "Deuce" },				//4

				{ 1, 0, "Fifteen-Love" }, 		//5
				{ 0, 1, "Love-Fifteen" }, 		//6
				{ 2, 0, "Thirty-Love" }, 		//7
				{ 0, 2, "Love-Thirty" },		//8
				{ 3, 0, "Forty-Love" }, 		//9
				{ 0, 3, "Love-Forty" }, 		//10
				{ 4, 0, "Win for player1" },	//11
				{ 0, 4, "Win for player2" },	//12

				{ 2, 1, "Thirty-Fifteen" }, 	//13
				{ 1, 2, "Fifteen-Thirty" }, 	//14
				{ 3, 1, "Forty-Fifteen" },		//15
				{ 1, 3, "Fifteen-Forty" }, 		//16
				{ 4, 1, "Win for player1" }, 	//17
				{ 1, 4, "Win for player2" },	//18

				{ 3, 2, "Forty-Thirty" }, 		//19
				{ 2, 3, "Thirty-Forty" }, 		//20
				{ 4, 2, "Win for player1" },	//21
				{ 2, 4, "Win for player2" },	//22

				{ 4, 3, "Advantage player1" }, 	//23
				{ 3, 4, "Advantage player2" }, 	//24
				{ 5, 4, "Advantage player1" },	//25
				{ 4, 5, "Advantage player2" }, 	//26
				{ 15, 14, "Advantage player1" },//27
				{ 14, 15, "Advantage player2" },//28

				{ 6, 4, "Win for player1" }, 	//29
				{ 4, 6, "Win for player2" }, 	//30
				{ 16, 14, "Win for player1" },	//31
				{ 14, 16, "Win for player2" }, 	//32
				});
	}

	public void checkAllScores(TennisGame game) {
		int highestScore = Math.max(player1Score, player2Score);
		for (int i = 0; i < highestScore; i++) {
			if (i < player1Score)
				game.wonPoint("player1");
			if (i < player2Score)
				game.wonPoint("player2");
		}
		assertEquals(expectedScore, game.getScore());
	}

	@Test
	public void checkAllScoresTennisGame1() {
		TennisGame1 game = new TennisGame1("player1", "player2");
		checkAllScores(game);
	}

	@Test
	public void checkAllScoresTennisGame2() {
		TennisGame2 game = new TennisGame2("player1", "player2");
		checkAllScores(game);
	}

	@Test
	public void checkAllScoresTennisGame3() {
		TennisGame3 game = new TennisGame3("player1", "player2");
		checkAllScores(game);
	}

}
