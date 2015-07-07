package tennis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static tennis.Points.*;

import org.junit.Test;

public class ScoreTranslatorTest {

	private ScoreTranslator translator = new ScoreTranslator();

	@Test
	public void tieScoreZerosIsLoveAll() {
		assertThat(translator.translate(LOVE, LOVE), is("Love-All"));
	}

	@Test
	public void tieScoreFifteenIsFifteenAll() {
		assertThat(translator.translate(FIFTEEN, FIFTEEN), is("Fifteen-All"));
	}

	@Test
	public void tieScoreThirtyIsThirtyAll() {
		assertThat(translator.translate(THIRTY, THIRTY), is("Thirty-All"));
	}

	@Test
	public void tieScoreFortyIsDeuce() {
		assertThat(translator.translate(FORTY, FORTY), is("Deuce"));
	}

	@Test
	public void tieScoreFivePointsEachIsDeuce() {
		assertThat(translator.translate(ADD1, ADD1), is("Deuce"));
	}

	@Test
	public void player1WinsByTwoIsWinForPlayer1() {
		assertThat(translator.translate(ADD1, THIRTY), is("Win for player1"));
	}

	@Test
	public void player1WinsByThreeIsWinForPlayer1() {
		assertThat(translator.translate(ADD1, FIFTEEN), is("Win for player1"));
	}

	@Test
	public void player1WinsByFourIsWinForPlayer1() {
		assertThat(translator.translate(ADD1, LOVE), is("Win for player1"));
	}
	
	@Test
	public void player1WinsImpossibyByFiveIsWinForPlayer1() {
		assertThat(translator.translate(ADD2, LOVE), is("Win for player1"));
	}
}
