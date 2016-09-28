package com.futuresailors.noughtsandcrosses.model;

public class NoughtsAndCrosses {

	private char turn = 'X';
	private char[][] grid = new char[3][3];
	private boolean hasEnded = false;
	private String result = "";
	
	public char getTurn(){
		return turn;
	}
	
	public String getResult(){
		return result;
	}
	
	public boolean getHasEnded(){
		return hasEnded;
	}
	
	public boolean checkIfWon(char player){
	
		if(checkRows(player) || checkCols(player) || checkDiags(player)){
			result = "" + player;
			return true;
		} else {
			boolean filled = true;
			for(int y = 0; y < grid.length; y++){
				for(int x = 0; x < grid[0].length; x++){
					if(grid[x][y] != 'X' && grid[x][y] != 'O'){
						filled = false;
					}
				}
			}
			if(filled){
				result = "Draw";
				return true;
			}
		}
		return false;
	}
	
	private boolean checkRows(char player){
		for(int y = 0; y < grid.length; y++){
			boolean match = true;
			for(int x = 0; x < grid[0].length; x++){
				if(grid[y][x] != player){
					match = false;
				}
			}
			if(match){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkCols(char player){
		for(int x = 0; x < grid.length; x++){
			boolean match = true;
			for(int y = 0; y < grid[0].length; y++){
				if(grid[y][x] != player){
					match = false;
				}
			}
			if(match){
				return true;
			}
		}
		return false;

	}
	
	private boolean checkDiags(char player){
		if(grid[1][1] == 0){
			return false;
		}
		if(grid[0][0] == grid[0][0] && grid[1][1] == grid[2][2]){
			return true;
		} else if(grid[2][0] == grid[1][1] && grid[0][2] == grid[1][1]){
			return true;
		}
		
		return false;
	}
	
	public void takeMove(int x, int y){
		if(y > grid.length - 1 || x > grid[0].length - 1){
			System.out.println("Coordinates larger than the grid.");
		} else {
			if(grid[y][x] == 0){
				
				grid[y][x] = turn;
				
				if(checkIfWon(turn)){
					hasEnded = true;
				}
				
				if(turn == 'X'){
					turn = 'O';
				} else {
					turn = 'X';
				}	
			} else {
				System.out.println("This space is taken.");
			}
		}
	}
	
	public char[][] getGrid(){
		return grid;
	}
	
}