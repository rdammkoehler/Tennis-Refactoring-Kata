package tennis;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class TennisGame4Test {

	private static final String PLAYERNAME_JOHN_MCENROE = "John McEnroe";

	private static final String PLAYERNAME_ANDRE_AGASSI = "Andre Agassi";

	private TennisGame4 game;

	private void addPointsToScore(String player, int pts) {
		for (int count = 0; count < pts; count++) {
			game.wonPoint(player);
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
		addPointsToScore(PLAYERNAME_ANDRE_AGASSI, 1);
		assertThat(scoreString(), is("Fifteen"));
	}

	@Test
	public void wonPointReturnsLoveForAPlayerWhoHasNotScored() {
		assertThat(scoreString(), is("Love"));
	}

	@Test
	public void playerWhoScoresTwiceHasThirtyPoints() {
		addPointsToScore(PLAYERNAME_ANDRE_AGASSI, 2);
		assertThat(scoreString(), is("Thirty"));
	}

	@Test
	public void playerWhoScoresThreeHasFortyPoints() {
		addPointsToScore(PLAYERNAME_ANDRE_AGASSI, 3);
		assertThat(scoreString(), is("Forty"));
	}

	@Test
	public void moreThanOnePlayerCanScore() {
		addPointsToScore(PLAYERNAME_ANDRE_AGASSI, 1);
		addPointsToScore(PLAYERNAME_JOHN_MCENROE, 1);
		assertThat(scoreString(), is("Fifteen"));
		assertThat(game.getCurrentPoints(PLAYERNAME_JOHN_MCENROE).toString(), is("Fifteen"));
	}

	@Test(expected = TennisGame4.ScoringException.class)
	public void threePlayersCannotPlay() {
		addPointsToScore(PLAYERNAME_ANDRE_AGASSI, 1);
		addPointsToScore(PLAYERNAME_JOHN_MCENROE, 1);
		addPointsToScore("John Conners", 1);
	}

}
