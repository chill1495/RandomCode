

public class test {

	public static void main(String[] args) {
		Board bb = new Board();		//3x3 board created if there are no parameters
		bb.randomize(10);			//randomized by the number of moves specified (if this is more than 10 it takes a while to find solution)
		System.out.println("Created a 3x3 board randomized by 10 moves:\n" + bb.toString());

		System.out.println("Solving with depth first traversal:\n");
		
		puzzleSolver solve2 = new puzzleSolver(bb);	//new depth first puzzle solver
		solve2.solve(bb);		//solve puzzle
		System.out.println("Solution found");
		solve2.getSolution();	//print solution
		
		System.out.println("-------------------------------------------------------");
		
		Board b = new Board();		
		System.out.println("Created new board of size 3x3:\n" + b.toString());
		b.randomize(100);	//breadth first can find solutions to more random boards (can go very high and still find solution quickly with 3x3)
		System.out.println("Board randomized by 100 moves:\n" + b.toString());
		
		puzzleSolverB solve = new puzzleSolverB(b);	//new breadth first puzzle solver
		System.out.println("Solving this puzzle with breadth first traversal:\n" + b.toString());
		solve.solve();		//solve the puzzle and print the solution
		
		System.out.println("-------------------------------------------------------");
		
		int arr [] = {3,2,1,0};
		Board b4 = new Board(arr);		//creating a board with a valid array
		System.out.println("Created a board from an array (2x2):\n" + b4.toString());
		
		System.out.println("Solve the 2x2 using bredth first traversal");
		puzzleSolverB solve4 = new puzzleSolverB(b4);
		solve4.solve();
		
		System.out.println("-------------------------------------------------------");
		
		Board b2 = new Board(15);		//parameter is an N value (board positions - 1)
		Board b3 = new Board(99);
		System.out.println("Created new boards of size 4x4 and 10x10: \n" + b2.toString() + "\n" + b3.toString());
		
		System.out.println("-------------------------------------------------------");
		
		System.out.println("randomizing the 10x10 by 10 moves and solving");
		b3.randomize(10);
		puzzleSolverB solve3 = new puzzleSolverB(b3);
		solve3.solve();		//breadth first solving the 10x10 puzzle
		
		System.out.println("-------------------------------------------------------");
		
		System.out.println("Creating a Board with an invalid dimension");
		try{
			Board y = new Board(10);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Board threw error because it has an illegal size");
		}
		System.out.println("Creating a board with an array of illegal size");
		try{
			int[] array = {2,3,4,5,0};		
			Board y = new Board(array);
		}
		catch(IllegalArgumentException f)
		{
			System.out.println("Board threw error because the array is not a valid size");
		}
		System.out.println("Creating board with duplicate value");
		try{
			int[] array = {2,2,3,4,0};
			Board y = new Board(array);
		}
		catch(IllegalArgumentException g)
		{
			System.out.println("Board threw error because of duplicate value");
		}	
		System.out.println("Creating board with missing value");
		try{
			int[] array = {0,1,2,4,5,6,7,8,9,10};
			Board y = new Board(array);
		}
		catch(IllegalArgumentException h)
		{
			System.out.println("Board threw error because of missing value");
		}
	}
}