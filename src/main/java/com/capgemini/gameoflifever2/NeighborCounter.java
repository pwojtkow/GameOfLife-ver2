package com.capgemini.gameoflifever2;

import java.util.HashMap;
import java.util.Map;

public class NeighborCounter {
//ASK about static method
	

	
	
	public int countAliveNeighbor(Map<Coordinate, Cell> board, Coordinate coordinates) {
		int xCoordinate = coordinates.getX();
		int yCoordinate = coordinates.getY();
		int counter = 0;
		
		for(int i=(xCoordinate-1);i<=(xCoordinate+1);i++){
			if(i<0 || i>=Game.numberOfColumns)
				continue;
			for(int j=yCoordinate-1;j<=yCoordinate+1;j++){
				if((j<0 || j>=Game.numberOfRows) || (i==xCoordinate && j==yCoordinate))
					continue;
				
				if(board.get(new Coordinate(i,j)).isAlive() == true)
					counter++;
			}
		}
		return counter;
	}

	private boolean isCoordinatesBelowZero(Coordinate neighborCoordinate) {
		return (neighborCoordinate.getX()) <0 || (neighborCoordinate.getY()<0);
	}
	
}
