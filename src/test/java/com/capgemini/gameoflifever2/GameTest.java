package com.capgemini.gameoflifever2;

import org.junit.*;

public class GameTest {

	@Test
	public void shouldCreatePlayingBoard() {
		// given
		int numbeOfColumns = 5;
		int numberOfRows = 5;
		// when
		Game game = new Game(numbeOfColumns, numberOfRows);
		int size = game.getBoard().size();
		// then
		Assert.assertEquals((numbeOfColumns * numberOfRows), size);
	}

	@Test
	public void shouldCountLivingNeighbors() {
		// given
		int numberOfColumns = 3;
		int numberOfRows = 3;
		int expectedValueOfLivingNeighbors = 2;
		Coordinate coordinates = new Coordinate(1, 1);
		// when
		Game game = new Game(numberOfColumns, numberOfRows);
		game.getBoard().get(new Coordinate(0, 1)).setAlive(true);
		game.getBoard().get(new Coordinate(2, 1)).setAlive(true);
		int numberOfAlife = game.neighborCounter.countAliveNeighbor(game.getBoard(), coordinates);
		// then
		Assert.assertEquals(expectedValueOfLivingNeighbors, numberOfAlife);
	}

	@Test
	public void shouldCountLivingNeighborsEdgeCaseBelowZero() {
		// given
		int numberOfColumns = 3;
		int numberOfRows = 3;
		int expectedValueOfLivingNeighbors = 2;
		Coordinate coordinates = new Coordinate(1, 0);
		// when
		Game game = new Game(numberOfColumns, numberOfRows);
		game.getBoard().get(new Coordinate(1, 1)).setAlive(true);
		game.getBoard().get(new Coordinate(2, 0)).setAlive(true);
		int numberOfAlife = game.neighborCounter.countAliveNeighbor(game.getBoard(), coordinates);
		// then
		Assert.assertEquals(expectedValueOfLivingNeighbors, numberOfAlife);
	}

	@Test
	public void shouldCountLivingNeighborsEdgeCaseUnderBoardSize() {
		// given
		int numberOfColumns = 3;
		int numberOfRows = 3;
		int expectedValueOfLivingNeighbors = 3;
		Coordinate coordinates = new Coordinate(2, 2);
		// when
		Game game = new Game(numberOfColumns, numberOfRows);
		game.getBoard().get(new Coordinate(1, 1)).setAlive(true);
		game.getBoard().get(new Coordinate(1, 2)).setAlive(true);
		game.getBoard().get(new Coordinate(2, 1)).setAlive(true);
		int numberOfAlife = game.neighborCounter.countAliveNeighbor(game.getBoard(), coordinates);
		// then
		Assert.assertEquals(expectedValueOfLivingNeighbors, numberOfAlife);
	}

	@Test
	public void shouldKillCellWhenLessThanTwoNeighbors() {
		// given
		int numberOfColumns = 3;
		int numberOfRows = 3;
		Coordinate coordinates = new Coordinate(1, 1);
		// when
		Game game = new Game(numberOfColumns, numberOfRows);
		game.getBoard().get(coordinates).setAlive(true);
		game.getBoard().get(new Coordinate(0, 1)).setAlive(true);
		game.checkAndChangeState(coordinates);
		boolean testingCellState = game.getBoard().get(coordinates).isAlive();
		// then
		Assert.assertFalse(testingCellState);
	}

	@Test
	public void shouldDoNothigWhenTwoOrThreeNeighbors() {
		// given
		int numberOfColumns = 3;
		int numberOfRows = 3;
		Coordinate coordinates = new Coordinate(2, 2);
		// when
		Game game = new Game(numberOfColumns, numberOfRows);
		game.getBoard().get(coordinates).setAlive(true);
		game.getBoard().get(new Coordinate(2, 1)).setAlive(true);
		game.getBoard().get(new Coordinate(1, 2)).setAlive(true);
		game.checkAndChangeState(coordinates);
		boolean testingCellState = game.getBoard().get(coordinates).isAlive();
		// then
		Assert.assertTrue(testingCellState);
	}

	@Test
	public void shouldKillCellWhenMoreThanThreeNeighbors() {
		// given
		int numberOfColumns = 3;
		int numberOfRows = 3;
		Coordinate coordinates = new Coordinate(1, 2);
		// when
		Game game = new Game(numberOfColumns, numberOfRows);
		game.getBoard().get(coordinates).setAlive(true);
		game.getBoard().get(new Coordinate(2, 2)).setAlive(true);
		game.getBoard().get(new Coordinate(2, 1)).setAlive(true);
		game.getBoard().get(new Coordinate(0, 2)).setAlive(true);
		game.getBoard().get(new Coordinate(1, 1)).setAlive(true);
		game.checkAndChangeState(coordinates);
		boolean testingCellState = game.getBoard().get(coordinates).isAlive();
		// then
		Assert.assertFalse(testingCellState);
	}

	@Test
	public void shouldRebornCellWhenThreeNeighbors() {
		// given
		int numberOfColumns = 3;
		int numberOfRows = 3;
		Coordinate coordinates = new Coordinate(0, 2);
		// when
		Game game = new Game(numberOfColumns, numberOfRows);
		game.getBoard().get(new Coordinate(0, 1)).setAlive(true);
		game.getBoard().get(new Coordinate(0, 2)).setAlive(true);
		game.getBoard().get(new Coordinate(1, 2)).setAlive(true);
		game.checkAndChangeState(coordinates);
		boolean testingCellState = game.getBoard().get(coordinates).isAlive();
		// then
		Assert.assertTrue(testingCellState);
	}
}
