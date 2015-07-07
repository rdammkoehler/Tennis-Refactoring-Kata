package tennis;

import org.junit.Test;

public class TennisGame1Test {

	@Test(expected=IllegalArgumentException.class)
	public void shouldRejectSinglePlayerGames() {
		new TennisGame1("Andre Agassi", "Andre Agassi");
	}
}
