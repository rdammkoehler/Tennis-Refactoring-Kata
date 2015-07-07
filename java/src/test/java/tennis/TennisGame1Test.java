package tennis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TennisGame1Test {

	private static final String PLAYER_MCENRO = "John McEnro";
	private static final String PLAYER_AGASSI = "Andre Agassi";

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectSinglePlayerGames() {
		new TennisGame1(PLAYER_AGASSI, PLAYER_AGASSI);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectNullPlayerNameOne() {
		new TennisGame1(null, PLAYER_AGASSI);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectNullPlayerNameTwo() {
		new TennisGame1(PLAYER_AGASSI, null);
	}

	@Test
	public void playerOneAndPlayerTwoCanBeDistinguished() {
		TennisGame game = new TennisGame1(PLAYER_AGASSI, PLAYER_MCENRO);
		game.wonPoint(PLAYER_AGASSI);
		game.wonPoint(PLAYER_AGASSI);
		game.wonPoint(PLAYER_AGASSI);
		game.wonPoint(PLAYER_AGASSI);
		assertThat(game.getScore(), is("Win for player1"));
	}

	@Test
	public void playerTwoAndPlayerOneCanBeDistinguished() {
		TennisGame game = new TennisGame1(PLAYER_AGASSI, PLAYER_MCENRO);
		game.wonPoint(PLAYER_MCENRO);
		game.wonPoint(PLAYER_MCENRO);
		game.wonPoint(PLAYER_MCENRO);
		game.wonPoint(PLAYER_MCENRO);
		assertThat(game.getScore(), is("Win for player2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void unknownPlayersCannotScore() {
		TennisGame game = new TennisGame1(PLAYER_AGASSI, PLAYER_MCENRO);
		game.wonPoint("John Conners");
	}
}
