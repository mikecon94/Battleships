package com.futuresailors.battleships.view;

import java.util.Scanner;

import com.futuresailors.battleships.model.NoughtsAndCrosses;

public class NoughtsAndCrossesConsole{

	public static void main(String[] args) {
		NoughtsAndCrosses nac = new NoughtsAndCrosses();
		System.out.println("Starting new game.");
		printGrid(nac.getGrid());
		Scanner in = new Scanner(System.in);
		while(nac.getHasEnded() == false){
			System.out.println("Player " + nac.getTurn() + "'s turn.");
			System.out.print("Enter X Coordinate: ");
			String moveX = in.nextLine();
			System.out.print("Enter Y Coordinate: ");
			String moveY = in.nextLine();
			nac.takeMove(Integer.parseInt(moveX), Integer.parseInt(moveY));
			printGrid(nac.getGrid());
		}
		System.out.println("Result: " + nac.getResult());
		in.close();
	}
	
	public static void printGrid(char[][] grid){
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[0].length; x++){
				if(grid[y][x] == 0){
					System.out.print(" ");
				} else {
					System.out.print(grid[y][x]);
				}
				
				if(x < grid[y].length - 1){
					System.out.print("|");
				}
				
			}
			System.out.println();
			if(y < grid.length - 1){
				for(int x = 0; x < grid.length; x++){
					System.out.print("--");
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	
}