import java.util.Arrays;
import java.util.Hashtable;

public class puzzleSolver {

	public final Board SOLUTION;	//solution board to check against, value set later when size is determined
	int length, size;
	Board moving, original;
	Hashtable<String, Board> list = new Hashtable<String,Board>();	//hashtable to store encountered boards for quickness
	Board solvedBoard;		//board returned by the solved method, used to print the solutions from
	boolean solved = false;
	
	public puzzleSolver(Board input)	//Depth First
	{
		int length = input.size();
		original = input;
		size = (int)(Math.sqrt(length));
		SOLUTION = new Board(length-1);
	}
	
	public boolean solve(Board b)
	{
		try
		{
		Board temp;
		moving = b;
		if (Arrays.equals(b.getArray(), SOLUTION.getArray()))		//end case, if we found solution
		{
			solvedBoard = b;
			solved = true;
		}
		else
		{
			if(solved == false && hasValidMoveAbove(b))
			{
				b.moveUp();							
				temp = new Board(b.getArray());		//create new board from move
				b.moveDown();
				temp.setParent(moving);				//set parent to the previous board
				temp.setHash(temp.toString());		//hash the table by it's toString
				list.put(temp.toString(), temp);	//add to hashtable
				solved = solve(temp);
				
			}
			if (solved == false && hasValidMoveRight(b))	//these 3 follow the same logic
			{
				b.moveRight();
				temp = new Board(b.getArray());
				b.moveLeft();
				temp.setParent(moving);
				temp.setHash(temp.toString());
				list.put(temp.toString(), temp);
				solved = solve(temp);
			}
			if (solved == false && hasValidMoveDown(b))
			{
				b.moveDown();
				temp = new Board(b.getArray());
				b.moveUp();
				temp.setParent(moving);
				temp.setHash(temp.toString());
				list.put(temp.toString(), temp);
				solved = solve(temp);
			}
			if (solved == false && hasValidMoveLeft(b))
			{
				b.moveLeft();
				temp = new Board(b.getArray());
				b.moveRight();
				temp.setParent(moving);
				temp.setHash(temp.toString());
				list.put(temp.toString(), temp);
				solved = solve(temp);
			}
		}
		return solved;
		}
		catch (StackOverflowError e)	//if we overflow, we return false and try another path
		{
			return false;
		}
	}
	
	public void getSolution()
	{
		printParentStack(solvedBoard);
	}
	
	private void printParentStack(Board b)		//prints all the parents of the solved board
	{
		if(b == null)
		{
			System.out.println("no solution found");
		}
		else
		{
			while(b.getParent() != null)
			{
				System.out.println(b.toString());
				b = b.getParent();
			}
			System.out.println(b.toString());
		}
	}
	
	private boolean hasValidMoveAbove(Board b)	//checks if moving the zero up has been done
	{
		Board nextMove = new Board(b.getArray());
		
		nextMove.moveUp();
		nextMove.setHash(nextMove.toString());
		if(!listContains(nextMove))
			return true;
		return false;
	}
		
	private boolean hasValidMoveRight(Board b)	//checks if moving the zero right has been done
	{
		Board nextMove = new Board(b.getArray());
		nextMove.moveRight();
		nextMove.setHash(nextMove.toString());
		if(!listContains(nextMove))
			return true;
		return false;
	}
	
	private boolean hasValidMoveDown(Board b)	//checks if moving the zero down has been done
	{
		Board nextMove = new Board(b.getArray());
		nextMove.moveDown();
		nextMove.setHash(nextMove.toString());
		if(!listContains(nextMove))
			return true;
		return false;
	}
	
	private boolean hasValidMoveLeft(Board b)	//checks if moving the zero left has been done
	{
		Board nextMove = new Board(b.getArray());
		nextMove.moveLeft();
		nextMove.setHash(nextMove.toString());
		
		if(!listContains(nextMove))
			return true;
		return false;
	}
	
	private boolean listContains(Board b)	//boards saved by their toString, easy check if they are in the hashtable
	{
		if(list.containsKey(b.toString()) == false)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
