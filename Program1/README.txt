README
Alan Hsueh, athsueh@ucsc.edu

Host name: unix2.ic.ucsc.edu


Usage:
Parameters are as follows: generations, cells, cell x, cell y. There are as
many x and y parameters as there are cells declared.


Program 1: Conway's Game of Life
The goal of this program was to reproduce Conway's Game of life in any programming language desired, as long as it ran within order O(n). The program starts by reading in either from the standard input, or a file, which is O(1). Then, the 2d array is initialized in order to place coordinates in. (makeGrid()) This runs at O(n^2) since the generated array is a 2d array. Fortunately, this is only run once throughout the whole program. The reading in loops over the amount of lines given, which length varies with respect to the amount of lines in the file. The first 2 lines are the generations and amount of cells, the latter of which are stored into an unitialized array. The loop will break when it reaches the end of file. Next, printArr() is called, which prints the array out with the rows labeled. This is a visual representation of what was read in. Now, the next generations must occur. The next loop is performed as many times as equal to the given generations. This is run in O(n). Within the loop, borderN(), propagate(), and printArr() are called one after another. borderN() preforms checkN() on all 8 surrounding neighbors of a live cell. This means that it runs as many times as there are live cells. It does 3 checks: to see if a cell is an existing live cell, an existing neighbor, or neither. If it is a live cell, the respective position in the uninitialized array of alive cells adds neighbors. The same applies for existing neighbors, except its position lies in the unitialized array of neighbors. If the cell is neither, it is added to said array. Effectively, a check for both live cells and potentially live cells is done at once. Afterwards, propagate() is called, which determines the result of the next generation. The rules of the game of life are performed on live and potentially live cells, which loop upon the two unitialized arrays of live cells and neighbor cells, respectively. Consideration is taken in to making sure all neighbor values are cleared for the next generation, as to not avoid incorrect neighbor values. Moreover, the neighbor array is completely cleared in preparation to accept new values. As stated previously, the printArr() method simply prints the array visually. Thus, the program runs in O(n) post-initialization.
