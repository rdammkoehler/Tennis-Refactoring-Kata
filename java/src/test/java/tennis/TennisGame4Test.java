package tennis;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class TennisGame4Test {

	private static final String PLAYERNAME_ANDRE_AGASSI = "Andre Agassi";

	private TennisGame4 game;
	
	private void addPointsToScore(int pts) {
		for(int count = 0; count < pts; count++ ) {
			game.wonPoint(PLAYERNAME_ANDRE_AGASSI);
		}
	}
	
	private String scoreString() {
		return game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString();
	}
	
	@Before
	public void beforeEach() {
		game = new TennisGame4();
	}
	
	@Test
	public void wonPointAddsPointToPlayerByName() {
		addPointsToScore(1);
		assertThat(scoreString(), is("Fifteen"));
	}


	@Test
	public void wonPointReturnsLoveForAPlayerWhoHasNotScored() {
		assertThat(scoreString(), is("Love"));
	}

	@Test
	public void playerWhoScoresTwiceHasThirtyPoints() {
		addPointsToScore(2);
		assertThat(scoreString(), is("Thirty"));
	}


	@Test
	public void playerWhoScoresThreeHasFortyPoints() {
		addPointsToScore(3);
		assertThat(scoreString(), is("Forty"));
	}
}
