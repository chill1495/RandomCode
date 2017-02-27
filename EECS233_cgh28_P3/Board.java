
public class Board {

	
	private int gameState [];			//saving the board in a 1D using indices to switch rows
	int size;						//since the board can be any size
	Board parent;					//used for printing the solution
	String hash;
	
	public Board()
	{
		size = 3;
		gameState = new int[9];
		for (int i = 0; i < gameState.length; i++)
		{
			gameState[i] = i;
		}
		parent = null;
	}
	
	public Board(int i)
	{
		int n = i+1;		//N is 1 less than the size needed
		if(((int)Math.sqrt(n)*(int)Math.sqrt(n)) == n)		//checking for nonsquare inputs
		{
			size = (int)Math.sqrt(n);
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
		gameState = new int[n];
		for (int j = 0; j < gameState.length; j++)
		{
			gameState[j] = j;
		}
		parent = null;
	}
	
	public Board(int[] input)
	{
		gameState = new int[input.length];
		if(((int)Math.sqrt(input.length)*(int)Math.sqrt(input.length)) == input.length)		//nonsquare input check
		{
			size = (int)Math.sqrt(input.length);
		}
		else
			throw new IllegalArgumentException();
		
		for(int j = 0; j < input.length; j++)		//duplicate check
		{
			for(int k = j+1; k < input.length; k++)
			{
				if (input[k] == input[j])
				{
					throw new IllegalArgumentException();
				}
			}
		}
		
		for(int l = 0; l < input.length; l++)		//missing value check
		{
			boolean contains = false;
			for (int m = 0; m < input.length; m++)
			{
				if (input[m] == l)
					contains = true;
			}
			if(contains == false)
			{
				throw new IllegalArgumentException();
			}
		}
		for(int i = 0; i < input.length; i++)
		{
			gameState[i] = input[i];
		}
		parent = null;
	}
	
	public int locateZero()			//find the zero to know which index in the array, or -1 if not found
	{
		for (int i = 0; i<gameState.length; i++)
		{
			if (gameState[i] == 0)
			{
				return i;
			}
		}
		return -1;
	}
	
	private void swap(int i, int j)
	{
		int temp = gameState[i];
		gameState[i] = gameState[j];
		gameState[j] = temp;
	}
	
	private int possibleMoves(int i)	//find the number of moves to help choose a random move
	{
		if (getAbove(i) != -1 && getBelow(i) != -1 && getLeft(i) != -1 && getRight(i) != -1)
		{
			return 4;
		}
		else if (getLeft(i) == -1 && getAbove(i) == -1)
			return 2;
		else if (getLeft(i) == -1 && getBelow(i) == -1)
			return 2;
		else if (getRight(i) == -1 && getAbove(i) == -1)
			return 2;
		else if (getRight(i) == -1 && getBelow(i) == -1)
			return 2;
		else
			return 3;
	}
	
	private void makeMove(int pos)	//chooses a number based on the number of possible 
	{								//moves and then makes a move based on it's position
		int move = (int)(possibleMoves(pos)*Math.random());
		if (pos == 0)		//top left corner
		{
			if (move == 0)
				moveRight();
			else
				moveDown();
		}
		else if (pos < size-1)	//top of board, but not corners
		{
			if (move == 0)
				moveRight();
			else if (move == 1)
				moveDown();
			else
				moveRight();
		}
		else if (pos == size-1)	//top right corner
		{
			if (move == 0)
				moveDown();
			else
				moveRight();
		}
		else if (pos == (size*size)-size) //bottom left corner
		{
			if (move == 0)
				moveUp();
			else
				moveRight();
		}
		else if (pos > ((size*size)-size) && pos < gameState.length-1)	//bottom of board but not corners
		{
			if (move == 0)
				moveUp();
			else if(move == 1)
				moveRight();
			else
				moveLeft();
		}
		else if (pos == gameState.length-1)	//bottom right corner
		{
			if (move == 0)
				moveUp();
			else
				moveLeft();
		}
		else if (pos%size == size-1)	//right side of board not corners
		{
			if(move == 0)
				moveUp();
			else if (move == 1)
				moveDown();
			else
				moveRight();
		}
		else if (pos%size == 0)			//left side of board not corners
		{
			if (move == 0)
				moveUp();
			else if(move == 1)
				moveRight();
			else
				moveDown();
		}
		else				//middle of the board
		{
			if (move == 0)
				moveUp();
			else if(move == 1)
				moveRight();
			else if (move ==2)
				moveDown();
			else
				moveLeft();
		}
	}
	
	public void setParent(Board b)
	{
		parent = b;
	}
	
	public Board getParent()
	{
		return parent;
	}
	
	public void setHash(String s)
	{
		hash = s;
	}
	
	public String getHash()
	{
		return hash;
	}
	
	public void randomize(int n)	//method to randomize the board
	{
		int x;
		for(int i = 0; i<n; i++)
		{
			x = locateZero();
			makeMove(x);
		}
	}
	
	public int size()
	{
		return gameState.length;
	}
	
	public int getValue(int pos)
	{
		return gameState[pos];
	}
	
	public int[] getArray()
	{
		return gameState;
	}
	
	public int getAbove(int pos)	//get value above zero
	{
		int result = pos - size;
		if (result < 0)
		{
			return -1;
		}
		else return gameState[pos-size];
	}
	
	public int getBelow(int pos)	//get value below zero
	{
		int result = pos+size;
		if (result >= size*size)
		{
			return -1;
		}
		else return gameState[pos+size];
	}
	
	public int getRight(int pos)	//get value right of zero
	{
		if (pos%size == size-1)
		{
			return -1;
		}
		else
			return gameState[pos+1];
	}
	
	public int getLeft(int pos)		//get value left of zero
	{
		if (pos%size ==0)
		{
			return -1;
		}
		else
			return gameState[pos-1];
	}
	
	public void moveLeft()
	{
		int x = locateZero();
		if(getLeft(x)!= -1)
		{
			swap(x, x-1);
		}
	}
	
	public void moveRight()
	{
		int x = locateZero();
		if(getRight(x) != -1)
		{
			swap(x, x+1);
		}
	}
	
	public void moveUp()
	{
		int x = locateZero();
		if(getAbove(x) != -1)
		{
			swap(x,x-size);
		}
	}
	
	public void moveDown()
	{
		int x = locateZero();
		if(getBelow(x) != -1)
		{
			swap(x, x+size);
		}
	}
	
	public String toString()		//prints board as a matrix
	{
		String output = "";
		for(int i = 0; i < gameState.length; i++)
		{
			if(i%size == size-1)
			{
				if (i == locateZero())
				{
					output += " \n";
				}
				else
					output+=gameState[i] + "\n";
			}
			else
				if(i==locateZero())
					output += "  ";
				else
					output += gameState[i] + " ";
		}
		return output;
	}
}
