package tennis;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class TennisGame4Test {

	@Test
	public void wonPointAddsPointToPlayerByName() {
		TennisGame4 game = new TennisGame4();
		game.wonPoint("Andre Agassi");
		assertThat(game.getCurrentPoints("Andre Agassi").toString(), is("Fifteen"));
	}
}
