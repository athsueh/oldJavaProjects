import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameOfLife {
	public static final int EXIT_SUCCESS = 0;
	public static final int EXIT_FAILURE = 1;
	public static int exit_status = EXIT_SUCCESS;

	public static final int dim = 155;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter Game of Life Specifications:");
		if (args.length == 0) {
			// Reading from STDIN
			readIn(new Scanner(in));
		} else {
			// Open file or return error
			String filename = args[0];
			try {
				Scanner infile = new Scanner(new File(filename));
				out.printf("FILE: %s%n", filename);
				readIn(infile);
				infile.close();
			} catch (IOException error) {
				exit_status = EXIT_FAILURE;
				err.printf(error.getMessage());
			}
		}
	}

	static void readIn(Scanner infile) {
		int generations = 0, cells = 0, temp, x, y;
		int linen = 1;
		// Make the 30x30 grid and populate alive cells.
		// The 1 is to compensate for values larger than the given space.
		Grid ggrid = new Grid();
		ggrid.makeGrid(dim, dim);

		while (infile.hasNextLine()) {
			// Read in generations
			if (linen == 1) {
				temp = infile.nextInt();
				generations = temp;
				// System.out.println("readingen");
			}
			// Read in cells
			else if (linen == 2) {
				temp = infile.nextInt();
				cells = temp;
				// System.out.println("readincell");
			} else {
				x = infile.nextInt();
				y = infile.nextInt();
				ggrid.addCell(x, y);
				// System.out.println("+1 line");
			}

			// System.out.println(linen + "," + cells);
			// Break loop when no more cells.
			// Add 2 because it starts after generations and cell number.

			if ((linen > 2) && (cells + 2 == linen))
				break;

			linen++;

		}
		System.out.println();
		ggrid.printArr();

		// Print the grid by the amount of generations.
		// Apply generations, then apply propagate.
		for (int i = 0; i < generations; i++) {
			ggrid.borderN();
			ggrid.propagate();
			ggrid.printArr();
		}

	}

}
