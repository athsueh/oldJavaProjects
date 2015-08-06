import java.util.ArrayList;

public class Grid {
	int[][] ggrid;

	// Initializes the starting grid.
	void makeGrid(int x, int y) {
		ggrid = new int[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				// System.out.println(i+","+j);
				ggrid[i][j] = 0;
			}
		}
	}

	ArrayList<Cell> liveC = new ArrayList<Cell>();
	ArrayList<Cell> neighC = new ArrayList<Cell>();

	void addCell(int x, int y) {
		// Adds alive cells into the grid, as well as into the uninit array.
		// The grid points back to the uninit array.
		// The size is always 1 more than the index.
		liveC.add(new Cell(x, y));
		// System.out.println(liveC.get(liveC.size() - 1).getX() + "   "
		// + liveC.get(liveC.size() - 1).getY());
		ggrid[x][y] = liveC.size() - 1;
	}

	void delCell(int pos) {
		// Deletes the last cell.
		// This is done by swapping the desired position with the last one,
		// then deleting the last one. (which is now the desired)
		int lastX = liveC.get(liveC.size() - 1).getX();
		int lastY = liveC.get(liveC.size() - 1).getY();
		ggrid[lastX][lastY] = pos;
		// Use Arraylist.set to swap the two positions.
		liveC.set(pos, liveC.get(liveC.size() - 1));
		liveC.remove(liveC.size() - 1);
	}

	// int neighbors;

	public void borderN() {
		// Runs checkNeighbors on all possible cells: 8 cells in a number pad
		// formation.
		// e.g. 1 is bottom left, 2 is bottom middle, etc.
		for (int i = 0; i < liveC.size(); i++) {
			checkN(i, -1, -1);
			checkN(i, -1, 0);
			checkN(i, 0, -1);
			checkN(i, -1, 1);
			checkN(i, 1, -1);
			checkN(i, 1, 0);
			checkN(i, 0, 1);
			checkN(i, 1, 1);
		}
	}

	boolean isLive(int x, int y) {
		// Checks if a position is already in the live array.
		// Make sure it is not out of bounds of the arraylist.

		if (ggrid[x][y] < liveC.size() && liveC.get(ggrid[x][y]).getX() == x
		        && liveC.get(ggrid[x][y]).getY() == y) {
			return true;
		} else {
			return false;
		}
	}

	boolean isNeigh(int x, int y) {
		// Same as previous except for neighbor array.
		if (neighC.isEmpty()) {
			return false;
		}

		if (ggrid[x][y] < neighC.size() && neighC.get(ggrid[x][y]).getX() == x
		        && neighC.get(ggrid[x][y]).getY() == y) {
			return true;
		} else {
			return false;
		}
	}

	void checkN(int i, int j, int k) {
		// Counts the number of neighbors and puts them into the 2nd uninit
		// array.
		// First gets the x/y values from the alive array.
		// Gets the positions for that cell and the one currently being checked.
		int xTemp = liveC.get(i).getX();
		int yTemp = liveC.get(i).getY();
		// System.out.println("xtemp,ytemp" + xTemp + "   " + yTemp);
		int xN = xTemp + j;
		int yN = yTemp + k;
		// If the neighbor is already an alive cell, increase its neighbors.
		if (isLive(xN, yN)) {
			liveC.get(i).plusN();
		}
		// If the neighbor is already in the neighbor arraylist, increase its
		// neighbors.
		else if (isNeigh(xN, yN)) {
			neighC.get(ggrid[xN][yN]).plusN();
		}
		// Last option is append to neighbor arraylist.
		// Define the reference in the grid, then add one.
		else {
			neighC.add(new Cell(xN, yN));
			ggrid[xN][yN] = neighC.size() - 1;
			neighC.get(ggrid[xN][yN]).plusN();
		}
	}

	void propagate() {
		// Perform ruling on alive cells.
		// Hold the size of the arraylist and loop.
		int temp = liveC.size();
		for (int i = 0; i < temp; i++) {
			// Cells with 2 or 3 neighbors live on to next generation.
			if (liveC.get(i).getN() == 2 || liveC.get(i).getN() == 3) {
				liveC.get(i).clearN();
				continue;
				// Any lower or greater number of neighbors causes death.
				// Change alive to 0, clear neighbor values.
			} else {
				liveC.get(i).dead();
				liveC.get(i).clearN();
				delCell(i);
				// Position has been swapped with last position during delete.
				// Thus, lower the temporary size.
				i--;
				temp--;
			}
		}
		// Perform ruling on neighbor cells.
		// Cells with 3 neighbors become alive.
		for (int i = 0; i < neighC.size(); i++) {
			// Add new neighbors to live arraylist.
			// Add the pointer from the grid to the arraylist.
			if (neighC.get(i).getN() == 3) {
				neighC.get(i).clearN();
				neighC.get(i).alive();
				liveC.add(neighC.get(i));
				// ggrid[x][y] = liveC.size() - 1;
				int newX = liveC.get(liveC.size() - 1).getX();
				int newY = liveC.get(liveC.size() - 1).getY();
				ggrid[newX][newY] = liveC.size() - 1;
			}
		}
		// Lastly, kill neighbors.
		neighC.clear();
	}

	void printArr() {
		// Print out the array, break every row.
		System.out.println("------------------------------");
		for (int i = 1; i < 31; i++) {

			for (int j = 1; j < 31; j++) {
				// System.out.print(ggrid[i][j]);
				if (isLive(i, j))
					System.out.print("1");
				else
					System.out.print("0");
			}
			System.out.print(" Row " + i);

			System.out.println();
		}
		System.out.println("------------------------------");
		// System.out.println(liveC.size());
	}
}
