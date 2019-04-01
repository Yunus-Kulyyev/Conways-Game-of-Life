package main;

import java.util.Random;

public class Grid {
	private int grid[][];
	private int width;
	private int height;
	
	public Grid(int height, int width) {
		this.width = width;
		this.height = height;
		
		grid = new int[height][width];
		initializeGrid();
	}
	
	public void initializeGrid() {
		/** Initial Empty Initialization*/
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				grid[i][j] = 0;
			}
		}
	}
	
	public void consoleDislpayGrid() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println("");
		}
	}
	
	public void populateGridPattern() {
		grid[height/2][width/2] = 1;
		grid[height/2-1][width/2] = 1;
		grid[height/2-1][width/2-1] = 1;
		grid[height/2-1][width/2+1] = 1;
	}
	
	public void populateGrid(int pop) {
		Random rand = new Random();
		for(int i = 0; i < pop; i ++) {
			int x;
			int y;
			do{
				x = rand.nextInt(width);
				y = rand.nextInt(height);
			}while(grid[x][y] == 1);
			
			grid[y][x] = 1;
		}
		
		//consoleDislpayGrid();
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public void simulateSphere() {
		int tempGrid[][] = new int[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int neighboorCount = 0;
				
				if(i-1 < 0) {		//South
					if(grid[height-1][j] >= 1) {
						neighboorCount++;
					}
				} else {
					if(grid[i-1][j] >= 1) {
						neighboorCount++;
					}
				}
				
				if(i+1 >= height) {		//North
					if(grid[0][j] >= 1) {
						neighboorCount++;
					}
				} else {
					if(grid[i+1][j] >= 1) {
						neighboorCount++;
					}
				}
				
				if(j-1 < 0) {		//West
					if(grid[i][width-1] >= 1) {
						neighboorCount++;
					}
				} else {
					if(grid[i][j-1] >= 1) {
						neighboorCount++;
					}
				}
				
				if(j+1 >= width) {		//East
					if(grid[i][0] >= 1) {
						neighboorCount++;
					}
				} else {
					if(grid[i][j+1] >= 1) {
						neighboorCount++;
					}
				}
				
				if(i-1 < 0 && j-1 < 0) {			//South-West
					if(grid[height-1][width-1] >= 1) {
						neighboorCount++;
					}
				} else if(i-1 < 0 && j-1 >= 0) {
					if(grid[height-1][j-1] >= 1) {
						neighboorCount++;
					}
				} else if(i-1 >= 0 && j-1 < 0) {
					if(grid[i-1][width-1] >= 1) {
						neighboorCount++;
					}
				} else if(i-1 >= 0 && j-1 >= 0) {
					if(grid[i-1][j-1] >= 1) {
						neighboorCount++;
					}
				}
				
				if(i+1 < height && j-1 < 0) {			//North-West
					if(grid[i+1][width-1] >= 1) {
						neighboorCount++;
					}
				} else if(i+1 < height && j-1 >= 0) {
					if(grid[i+1][j-1] >= 1) {
						neighboorCount++;
					}
				} else if(i+1 >= height && j-1 < 0) {
					if(grid[0][width-1] >= 1) {
						neighboorCount++;
					}
				} else if(i+1 >= height && j-1 >= 0) {
					if(grid[0][j-1] >= 1) {
						neighboorCount++;
					}
				}
				
				if(i-1 < 0 && j+1 >= width) {			//South-West
					if(grid[height-1][0] >= 1) {
						neighboorCount++;
					}
				} else if(i-1 < 0 && j+1 < width) {
					if(grid[height-1][j+1] >= 1) {
						neighboorCount++;
					}
				} else if(i-1 >= 0 && j+1 < width) {
					if(grid[i-1][j+1] >= 1) {
						neighboorCount++;
					}
				} else if(i-1 >= 0 && j+1 >= width) {
					if(grid[i-1][0] >= 1) {
						neighboorCount++;
					}
				}
				
				if(i+1 < height && j+1 < width) {			//North-East
					if(grid[i+1][j+1] >= 1) {
						neighboorCount++;
					}
				} else if(i+1 < height && j+1 >= width) {
					if(grid[i+1][0] >= 1) {
						neighboorCount++;
					}
				} else if(i+1 >= height && j+1 < width) {
					if(grid[0][j+1] >= 1) {
						neighboorCount++;
					}
				} else if(i+1 >= height && j+1 >= width) {
					if(grid[0][0] >= 1) {
						neighboorCount++;
					}
				}
				
				if(neighboorCount < 2) {
					tempGrid[i][j] = 0;
				} 
				else if(neighboorCount == 3) {
					tempGrid[i][j] = 2;
				}
				else if(neighboorCount == 2) {
					if(grid[i][j] == 1 || grid[i][j] == 2) {
						tempGrid[i][j] = 1;
					}
				}
				else if(neighboorCount > 3) {
					tempGrid[i][j] = 0;
				}
			}
		}
		
		for(int i = 0; i < tempGrid.length; i++) {
			for(int j = 0; j < tempGrid[0].length; j++) {
				grid[i][j] = tempGrid[i][j];
			}
		}
	}
	
	public void simulate() {
		int tempGrid[][] = new int[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int neighboorCount = 0;
				
				if(i-1 >= 0) {		//South
					if(grid[i-1][j] == 1) {
						neighboorCount++;
					}
				}
				if(i+1 < height) {	//North
					if(grid[i+1][j] == 1) {
						neighboorCount++;
					}
				}
				if(j-1 >= 0) {		//West
					if(grid[i][j-1] == 1) {
						neighboorCount++;
					}
				}
				if(j+1 < width) {	//East
					if(grid[i][j+1] == 1) {
						neighboorCount++;
					}
				}
				if((i-1 >= 0) && (j-1 >= 0)) {		 //South-West
					if(grid[i-1][j-1] == 1) {
						neighboorCount++;
					}
				}
				if((i+1 < height) && (j-1 >= 0)) {	 //North-West
					if(grid[i+1][j-1] == 1) {
						neighboorCount++;
					}
				}
				if((i-1 >= 0) && (j+1 < width)) {	 //South-East
					if(grid[i-1][j+1] == 1) {
						neighboorCount++;
					}
				}
				if((i+1 < height) && (j+1 < width)) {//North-East
					if(grid[i+1][j+1] == 1) {
						neighboorCount++;
					}
				}
				
				if(neighboorCount < 2) {
					tempGrid[i][j] = 0;
				} 
				else if(neighboorCount == 3) {
					tempGrid[i][j] = 1;
				}
				else if(neighboorCount >= 2 && neighboorCount <= 3) {
					tempGrid[i][j] = grid[i][j];
				}
				else if(neighboorCount > 3) {
					tempGrid[i][j] = 0;
				}
			}
		}
		
		for(int i = 0; i < tempGrid.length; i++) {
			for(int j = 0; j < tempGrid[0].length; j++) {
				grid[i][j] = tempGrid[i][j];
			}
		}
	}
	
}
