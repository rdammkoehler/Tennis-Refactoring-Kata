package tennis;

import org.junit.Test;

public class TennisGame1Test {

	@Test(expected=IllegalArgumentException.class)
	public void shouldRejectSinglePlayerGames() {
		new TennisGame1("Andre Agassi", "Andre Agassi");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRejectNullPlayerNameOne() {
		new TennisGame1(null,"Andre Agassi");
	}
}
