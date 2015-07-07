package tennis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TennisGame1Test {

	private static final String PLAYER1 = "Andre Agassi";
	private static final String PLAYER2 = "John McEnro";
	private TennisGame game = new TennisGame1(PLAYER1, PLAYER2);

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectSinglePlayerGames() {
		new TennisGame1(PLAYER1, PLAYER1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectNullPlayerNameOne() {
		new TennisGame1(null, PLAYER1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectNullPlayerNameTwo() {
		new TennisGame1(PLAYER1, null);
	}

	@Test
	public void playerOneAndPlayerTwoCanBeDistinguished() {
		game.wonPoint(PLAYER1);
		game.wonPoint(PLAYER1);
		game.wonPoint(PLAYER1);
		game.wonPoint(PLAYER1);
		assertThat(game.getScore(), is("Win for player1"));
	}

	@Test
	public void playerTwoAndPlayerOneCanBeDistinguished() {
		game.wonPoint(PLAYER2);
		game.wonPoint(PLAYER2);
		game.wonPoint(PLAYER2);
		game.wonPoint(PLAYER2);
		assertThat(game.getScore(), is("Win for player2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void unknownPlayersCannotScore() {
		game.wonPoint("John Conners");
	}
}
