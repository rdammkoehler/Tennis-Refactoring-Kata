package tennis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ScoreTranslatorTest {

	private ScoreTranslator translator = new ScoreTranslator();

	@Test
	public void tieScoreZerosIsLoveAll() {
		assertThat(translator.translate(0, 0), is("Love-All"));
	}

	@Test
	public void tieScoreFifteenIsFifteenAll() {
		assertThat(translator.translate(1, 1), is("Fifteen-All"));
	}

	@Test
	public void tieScoreThirtyIsThirtyAll() {
		assertThat(translator.translate(2, 2), is("Thirty-All"));
	}
}
