package tennis;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class TennisGame4Test {

	private static final String PLAYERNAME_ANDRE_AGASSI = "Andre Agassi";

	@Test
	public void wonPointAddsPointToPlayerByName() {
		TennisGame4 game = new TennisGame4();
		game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		assertThat(game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString(), is("Fifteen"));
	}
	
	@Test
	public void wonPointReturnsLoveForAPlayerWhoHasNotScored() {
		TennisGame4 game = new TennisGame4();
		assertThat(game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString(), is("Love"));
	}
	
}
