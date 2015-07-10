package tennis;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class TennisGame4Test {

	private static final String PLAYERNAME_ANDRE_AGASSI = "Andre Agassi";

	private TennisGame4 game;
	
	@Before
	public void beforeEach() {
		game = new TennisGame4();
	}
	
	@Test
	public void wonPointAddsPointToPlayerByName() {
		game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		assertThat(game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString(), is("Fifteen"));
	}

	@Test
	public void wonPointReturnsLoveForAPlayerWhoHasNotScored() {
		assertThat(game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString(), is("Love"));
	}

	@Test
	public void playerWhoScoresTwiceHasThirtyPoints() {
		game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		assertThat(game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString(), is("Thirty"));
	}


	@Test
	public void playerWhoScoresThreeHasFortyPoints() {
		game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		assertThat(game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString(), is("Forty"));
	}
}
