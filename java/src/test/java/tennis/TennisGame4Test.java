package tennis;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class TennisGame4Test {

	private static final int AD = 4;
	private static final int LOVE = 0;
	private static final int FIFTEEN = 1;
	private static final int THIRTY = 2;
	private static final int FORTY = 3;
	private static final String PLAYERNAME_JOHN_CONNERS = "John Conners";
	private static final String PLAYERNAME_JOHN_MCENROE = "John McEnroe";
	private static final String PLAYERNAME_ANDRE_AGASSI = "Andre Agassi";

	private TennisGame4 game;

	private void makeGameScore(int player1score, int player2score) {
		addPointsToPlayersScore(PLAYERNAME_ANDRE_AGASSI, player1score);
		addPointsToPlayersScore(PLAYERNAME_JOHN_MCENROE, player2score);
	}

	private void addPointsToPlayersScore(String player, int pts) {
		for (int count = 0; count < pts; count++) {
			game.wonPoint(player);
		}
	}

	private String scoreString() {
		return game.getCurrentPoints(PLAYERNAME_ANDRE_AGASSI).toString();
	}

	@Before
	public void beforeEach() {
		game = new TennisGame4(PLAYERNAME_ANDRE_AGASSI, PLAYERNAME_JOHN_MCENROE);
	}

	@Test
	public void wonPointAddsPointToPlayerByName() {
		makeGameScore(FIFTEEN, LOVE);
		assertThat(scoreString(), is("Fifteen"));
	}

	@Test
	public void wonPointReturnsLoveForAPlayerWhoHasNotScored() {
		assertThat(scoreString(), is("Love"));
	}

	@Test(expected = TennisGame4.PlayerNotFoundException.class)
	public void unknownPlayerScoreRequestGeneratesException() {
		game.getCurrentPoints(PLAYERNAME_JOHN_CONNERS);
	}

	@Test
	public void playerWhoScoresTwiceHasThirtyPoints() {
		makeGameScore(THIRTY, LOVE);
		assertThat(scoreString(), is("Thirty"));
	}

	@Test
	public void playerWhoScoresThreeHasFortyPoints() {
		makeGameScore(FORTY, LOVE);
		assertThat(scoreString(), is("Forty"));
	}

	@Test
	public void moreThanOnePlayerCanScore() {
		makeGameScore(FIFTEEN, FIFTEEN);
		assertThat(scoreString(), is("Fifteen"));
		assertThat(game.getCurrentPoints(PLAYERNAME_JOHN_MCENROE).toString(), is("Fifteen"));
	}

	@Test(expected = TennisGame4.ScoringException.class)
	public void threePlayersCannotPlay() {
		addPointsToPlayersScore(PLAYERNAME_JOHN_CONNERS, 1);
	}

	@Test(expected = TennisGame4.GameConfigurationException.class)
	public void playerNamesMustBeUnique() {
		new TennisGame4(PLAYERNAME_ANDRE_AGASSI, PLAYERNAME_ANDRE_AGASSI);
	}

	@Test(expected = TennisGame4.GameConfigurationException.class)
	public void player1CannotBeNull() {
		new TennisGame4(null, PLAYERNAME_ANDRE_AGASSI);
	}

	@Test(expected = TennisGame4.GameConfigurationException.class)
	public void player2CannotBeNull() {
		new TennisGame4(PLAYERNAME_ANDRE_AGASSI, null);
	}

	@Test
	public void gameStartsAtLoveAll() {
		assertThat(game.getScore(), is("Love-All"));
	}

	@Test
	public void player1ScoresOnceAndScoreIsFifteenLove() {
		makeGameScore(FIFTEEN, LOVE);
		assertThat(game.getScore(), is("Fifteen-Love"));
	}

	@Test
	public void eachPlayerScoresAndScoreIsFifteenAll() {
		makeGameScore(FIFTEEN, FIFTEEN);
		assertThat(game.getScore(), is("Fifteen-All"));
	}

	@Test
	public void fortyAllIsDeuce() {
		makeGameScore(FORTY, FORTY);
		assertThat(game.getScore(), is("Deuce"));
	}

	@Test
	public void addAddIsDeuce() {
		makeGameScore(AD, AD);
		assertThat(game.getScore(), is("Deuce"));
	}

	@Test
	public void player1Wins4to0() {
		makeGameScore(AD, LOVE);
		assertThat(game.getScore(), is("Win for player1"));
	}

	@Test
	public void player2Wins4to0() {
		makeGameScore(LOVE, AD);
		assertThat(game.getScore(), is("Win for player2"));
	}

	@Test
	public void player1Wins5to3() {
		makeGameScore(AD + 1, FORTY);
		assertThat(game.getScore(), is("Win for player1"));
	}

}
