package com.capgemini.gameoflifever2;

import java.util.HashMap;
import java.util.Map;

public class Game {

	public static int numberOfColumns;
	public static int numberOfRows;
	
	private static final int NUMBER_OF_NEIGHBOR_NEEDED_TO_DIE_FROM_CROWD = 3;
	private static final int NUMBER_OF_NEIGHBOR_NEEDED_TO_DIE_FROM_LONELINESS = 2;
	private static final int NUMBER_OF_NEIGHBORS_NEEDED_TO_REBORN = 3;
	private HashMap<Coordinate, Cell> board;
	private NeighborCounter neighborCounter = new NeighborCounter();
	

	public Game(int numbersOfColumns, int numberOfRows) {
		board = new HashMap<Coordinate, Cell>();
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numbersOfColumns;
		for (int i = 0; i < numbersOfColumns; i++) {
			for (int j = 0; j < numberOfRows; j++) {
				Cell cell = new Cell(i, j, false);
				board.put(cell.getCoordinates(), cell);
			}
		}
	}

	private boolean isCellAlive(Coordinate coordinates) {
		boolean cellState = board.get(coordinates).isAlive();
		if (cellState) {
			return true;
		} else {
			return false;
		}
	}

	private void changeCellState(Coordinate coordinates) {
		boolean state = board.get(coordinates).isAlive();
		board.get(coordinates).setAlive(!state);
	}

	public void changeGeneration() {
		for (Coordinate coordinates : board.keySet()) {
			changeState(coordinates);
		}
	}

	private void changeState(Coordinate coordinates) {
		if ((isCellAlive(coordinates) == false)
				&& (neighborCounter.countAliveNeighbor(board,
						coordinates) == NUMBER_OF_NEIGHBORS_NEEDED_TO_REBORN)) {
			changeCellState(coordinates);
		} else if ((isCellAlive(coordinates))
				&& (neighborCounter.countAliveNeighbor(board,
						coordinates) < NUMBER_OF_NEIGHBOR_NEEDED_TO_DIE_FROM_LONELINESS)) {
			changeCellState(coordinates);
		} else if ((isCellAlive(coordinates))
				&& (neighborCounter.countAliveNeighbor(board,
						coordinates) > NUMBER_OF_NEIGHBOR_NEEDED_TO_DIE_FROM_CROWD)) {
			changeCellState(coordinates);
		}
	}

	/**
	 * @return actual state of gaming board
	 */
	public Map<Coordinate, Cell> getBoard() {
		return board;
	}
	
}
