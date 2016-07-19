package com.capgemini.gameoflifever2;

import java.util.Map;

public class NeighborCounter {
	public int countAliveNeighbor(final Map<Coordinate, Cell> board,
			Coordinate coordinates) {
		int xCoordinate = coordinates.getX();
		int yCoordinate = coordinates.getY();
		int counter = 0;

		for (int i = (xCoordinate - 1); i <= (xCoordinate + 1); i++) {
			if (i < 0 || i >= Game.numberOfColumns) {
				continue;
			}
			for (int j = yCoordinate - 1; j <= yCoordinate + 1; j++) {
				if ((j < 0 || j >= Game.numberOfRows)
						|| (i == xCoordinate && j == yCoordinate)) {
					continue;
				}
				if (board.get(new Coordinate(i, j)).isAlive() == true) {
					counter++;
				}
			}
		}
		return counter;
	}
}
