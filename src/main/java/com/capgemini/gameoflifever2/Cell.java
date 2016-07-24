package com.capgemini.gameoflifever2;

public class Cell {

	private Coordinate coordinates;
	private boolean isAlive;

	public Cell(int x, int y, boolean isAlive) {
		this.isAlive = isAlive;
		coordinates = new Coordinate(x, y);
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
