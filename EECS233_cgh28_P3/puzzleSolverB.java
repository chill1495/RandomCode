import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class puzzleSolverB {

	public ArrayList<Board> list = new ArrayList<Board>();	//queue to keep order of checking
	public Hashtable<String, Board> table = new Hashtable<String, Board>();	//hashtable to save encountered boards
	public final Board SOLUTION;	//correct solution, will be set when the size of the board is determined
	int size, length;		//length is the total length(4,9,16,etc) size is the size of a side of the board
	boolean found = false;
	Board parent;
	
	public puzzleSolverB(Board input)	//Breadth first
	{
		length = input.size();
		size = (int)Math.sqrt(length);
		SOLUTION = new Board(length -1);
		list.add(input);
		table.put(input.toString(), input);
		parent = input;
	}
	
	public Board solve()
	{
		Board moving = list.get(0);	
		Board temp;
		while(list.size() != 0 && found != true)	//if list.size() == 0, there are no more moves to check
		{
			moving = list.get(0);
			parent = moving;
			if(Arrays.equals(SOLUTION.getArray(), moving.getArray()))	//solution found, return that board
			{
				System.out.println("solution found");
				found = true;
				printParentStack(moving);
				return moving;
			}
			else
			{
				if(hasValidMoveAbove(moving))	//if moving one move up hasn't been done,
				{								//add that to the list and the table
					moving.moveUp();
					temp = new Board(moving.getArray());
					moving.moveDown();
					temp.setParent(parent);
					list.add(temp);
					table.put(temp.toString(), temp);
				}
				if(hasValidMoveRight(moving))
				{
					moving.moveRight();
					temp = new Board(moving.getArray());
					moving.moveLeft();
					temp.setParent(parent);
					list.add(temp);
					table.put(temp.toString(), temp);
				}
				if(hasValidMoveDown(moving))
				{
					moving.moveDown();
					temp = new Board(moving.getArray());
					moving.moveUp();
					temp.setParent(parent);
					list.add(temp);
					table.put(temp.toString(), temp);
				}
				if(hasValidMoveLeft(moving))
				{
					moving.moveLeft();
					temp = new Board(moving.getArray());
					moving.moveRight();
					temp.setParent(parent);
					list.add(temp);
					table.put(temp.toString(), temp);
				}
				list.remove(0);			//remove used value
			}
		}
		return null;
	}
	
	public void printParentStack(Board b)	//prints parents to show solutions
	{
		if(b == null)
			System.out.println("no solution found");
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
	
	private boolean hasValidMoveAbove(Board b)	//checks if move above has been done
	{
		Board nextMove = new Board(b.getArray());
		
		nextMove.moveUp();
		nextMove.setHash(nextMove.toString());
		if(!listContains(nextMove))
			return true;
		return false;
	}
		
	private boolean hasValidMoveRight(Board b)	//checks if move right has been done
	{
		Board nextMove = new Board(b.getArray());
		nextMove.moveRight();
		nextMove.setHash(nextMove.toString());
		if(!listContains(nextMove))
			return true;
		return false;
	}
	
	private boolean hasValidMoveDown(Board b)	//checks if move down has been done
	{
		Board nextMove = new Board(b.getArray());
		nextMove.moveDown();
		nextMove.setHash(nextMove.toString());
		if(!listContains(nextMove))
			return true;
		return false;
	}
	
	private boolean hasValidMoveLeft(Board b)	//checks if move left has been done
	{
		Board nextMove = new Board(b.getArray());
		nextMove.moveLeft();
		nextMove.setHash(nextMove.toString());
		if(!listContains(nextMove))
			return true;
		return false;
	}
	
	private boolean listContains(Board b)		//if the table has the board, we've used it before
	{
		if(table.containsKey(b.toString()) == false)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
}
