package com.capgemini.gameoflifever2;

import java.util.HashMap;
import java.util.Map;

public class Game {

	HashMap<Coordinate, Cell> board;
	NeighborCounter neighborCounter = new NeighborCounter();
	public static int numberOfColumns;
	public static int numberOfRows;

	public Game(int numbersOfColumns, int numberOfRows) {
		board = new HashMap<Coordinate, Cell>();
		this.numberOfRows=numberOfRows;
		this.numberOfColumns=numbersOfColumns;
		for (int i = 0; i < numbersOfColumns; i++) {
			for (int j = 0; j < numberOfRows; j++) {
				Cell cell = new Cell(i, j, false);
				board.put(cell.getCoordinates(), cell);
			}
		}
	}

	public boolean isCellAlive(Coordinate coordinates) {
		boolean cellState = board.get(coordinates).isAlive();
		if (cellState == true)
			return true;
		else
			return false;
	}

	public void changeCellState(Coordinate coordinates) {
		boolean state = board.get(coordinates).isAlive();
		board.get(coordinates).setAlive(!state);
	}

	public void changeGeneration() {
		for (Coordinate coordinates : board.keySet()) {
			//TODO change this name, two thinks to do
			checkAndChangeState(coordinates);
		}
	}

	public void checkAndChangeState(Coordinate coordinates) {
		if ((isCellAlive(coordinates)==false) && (neighborCounter.countAliveNeighbor(board, coordinates) == 3))
			changeCellState(coordinates);
		else if ((isCellAlive(coordinates)) && (neighborCounter.countAliveNeighbor(board, coordinates) < 2))
			changeCellState(coordinates);
		else if ((isCellAlive(coordinates)) && (neighborCounter.countAliveNeighbor(board, coordinates) > 3))
			changeCellState(coordinates);
	}


	public Map<Coordinate, Cell> getBoard() {
		return board;
	}

	public void setBoard(HashMap<Coordinate, Cell> board) {
		this.board = board;
	}

}
