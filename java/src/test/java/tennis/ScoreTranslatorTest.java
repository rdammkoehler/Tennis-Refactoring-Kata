package tennis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static tennis.Points.*;

import org.junit.Test;

public class ScoreTranslatorTest {

	private static final String PLAYER1 = "Agassi";
	private static final String PLAYER2 = "McEnroe";

	private String translate(Points player1Score, Points player2Score) {
		ScoreKeeper scoreKeeper = new ScoreKeeper(PLAYER1, PLAYER2);
		while (player1Score.greaterThan(scoreKeeper.scoreOfPlayer1())) {
			scoreKeeper.incrementScoreOf(PLAYER1);
		}
		while (player2Score.greaterThan(scoreKeeper.scoreOfPlayer2())) {
			scoreKeeper.incrementScoreOf(PLAYER2);
		}
		return new ScoreTranslator(scoreKeeper).translate(scoreKeeper);
	}

	@Test
	public void tieScoreZerosIsLoveAll() {
		assertThat(translate(LOVE, LOVE), is("Love-All"));
	}

	@Test
	public void tieScoreFifteenIsFifteenAll() {
		assertThat(translate(FIFTEEN, FIFTEEN), is("Fifteen-All"));
	}

	@Test
	public void tieScoreThirtyIsThirtyAll() {
		assertThat(translate(THIRTY, THIRTY), is("Thirty-All"));
	}

	@Test
	public void tieScoreFortyIsDeuce() {
		assertThat(translate(FORTY, FORTY), is("Deuce"));
	}

	@Test
	public void tieScoreFivePointsEachIsDeuce() {
		assertThat(translate(ADD1, ADD1), is("Deuce"));
	}

	@Test
	public void player1WinsByTwoIsWinForPlayer1() {
		assertThat(translate(ADD1, THIRTY), is("Win for player1"));
	}

	@Test
	public void player1WinsByThreeIsWinForPlayer1() {
		assertThat(translate(ADD1, FIFTEEN), is("Win for player1"));
	}

	@Test
	public void player1WinsByFourIsWinForPlayer1() {
		assertThat(translate(ADD1, LOVE), is("Win for player1"));
	}

	@Test
	public void player1WinsImpossibyByFiveIsWinForPlayer1() {
		assertThat(translate(ADD2, LOVE), is("Win for player1"));
	}

	@Test
	public void player1LosesImpossiblyByFiveIsWinForPlayer2() {
		assertThat(translate(LOVE, ADD2), is("Win for player2"));
	}

	@Test
	public void player1AheadBy1AfterDeuceIsAdvantagePlayer1() {
		assertThat(translate(ADD1, FORTY), is("Advantage player1"));
	}

	@Test
	public void player2AheadBy1AfterDeuceIsAdvantagePlayer2() {
		assertThat(translate(FORTY, ADD1), is("Advantage player2"));
	}

	@Test
	public void player1AheadBy2AfterDeuceIsWinForPlayer1() {
		assertThat(translate(ADD2, FORTY), is("Win for player1"));
	}

	@Test
	public void player2AheadBy2AfterDeuceIsWinforPlayer2() {
		assertThat(translate(FORTY, ADD2), is("Win for player2"));
	}
}
