package tennis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TennisGame1Test {

	private static final String PLAYER_NAME = "Andre Agassi";

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectSinglePlayerGames() {
		new TennisGame1(PLAYER_NAME, PLAYER_NAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectNullPlayerNameOne() {
		new TennisGame1(null, PLAYER_NAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectNullPlayerNameTwo() {
		new TennisGame1(PLAYER_NAME, null);
	}
	
	@Test
	public void playerOneAndPlayerTwoCanBeDistinguished() {
		TennisGame game = new TennisGame1(PLAYER_NAME, "John McEnro");
		game.wonPoint(PLAYER_NAME);
		game.wonPoint(PLAYER_NAME);
		game.wonPoint(PLAYER_NAME);
		game.wonPoint(PLAYER_NAME);
		assertThat(game.getScore(), is("Win for player1"));
	}
}
