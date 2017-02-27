
public class NumLinkedList implements NumList {

	private int numAdded;					//keep count of number of nodes for ease in traversing the list
	private Node head;						//separate Node class to hold the elements of the linked list
	
	public NumLinkedList()
	{
		numAdded = 0;
		head = new Node();
	}
	public int size()
	{
		return numAdded;
	}
	
	public void add(double value)
	{
		numAdded++;						//node is being added
		Node pointer = head;			//start at the beginning
		if(numAdded == 1)				//if this is the first item added, it becomes the head
		{
			head = new Node(value, null);
		}
		else if (numAdded == 2)			//if this is the second item added, it becomes the reference for head
		{
			head.setNext(new Node(value,null));
		}
		else
		{
		for (int i = 1; i < numAdded -1; i++)	//otherwise go through the list to the last item
		{
			pointer = pointer.getNext();
		}
		
		pointer.setNext(new Node(value, null)); // set the index of the last item to the new Node
		}
	}
	
	public void insert (int i, double value)
	{
		numAdded++;				//item added
		Node pointer = head;	//temp variable to start at beginning
		if (i > numAdded)		//if i is more than the amount of Nodes, just put on the end
		{
			add(value);
		}
		else
		{
			for (int k = 0; k < i; k++)		//else go through the list to the node before the index where we add the new item
			{
				pointer.setNext(pointer.getNext());
			}
			pointer.setNext(new Node(value, pointer.getNext()));	//set the item before the index to point at the new item and have the new item point at the item that used to be in its position
		}
	}
	
	public void remove (int i)
	{
		Node pointer = head;		//start at beginning 
		if (i < numAdded)			//only remove if i is a valid index
		{
			for (int j = 0; j < i-1; j++)
			{
				pointer = pointer.getNext(); 		//move through the list until you get to the index before the item to remove
			}
			pointer.setNext(pointer.getNext().getNext());  //change from pointing at the item the user is removing to pointing at the item following it
			numAdded--;					//item removed
		}
	}
	
	public boolean contains(double value)
	{
		Node pointer = head;
		for(int i = 0; i < numAdded; i++)		//traverse through the nodes
		{
			if (pointer.getValue() == value)			//if a node has that value --> true
			{
				return true;
			}
			pointer = pointer.getNext();
		}
		return false;						// else --> false
	}
	
	public double lookup (int i) throws IndexOutOfBoundsException
	{
		if (i < numAdded)			//if i is a valid index
		{
			Node pointer = head;
			for (int j = 0; j < i; j++)				//move through the list to index i
			{
				pointer = pointer.getNext();
			}
			return pointer.getValue();				//return value of i
		}
		else
		throw new IndexOutOfBoundsException();
	}
	
	public boolean equals (NumList otherList)
	{
		if (otherList.size() != numAdded)
		{
			return false;
		}
		else
		{
			Node pointer = head;
			for (int j = 0; j < numAdded - 1; j++)
			{
				if (pointer.getValue() != otherList.lookup(j))
				{
					return false;
				}
				pointer = pointer.getNext();
			}
		}
		return true;
	}
	
	public void removeDuplicates()
	{
		for (int i = 0; i < numAdded; i++)
		{
			for (int j = i+1; j < numAdded; j++)
			{
				if (lookup(i) == lookup(j))
				{
					remove(j);
				}
			}
		}
	}
	
	public String toString()
	{
		String x = "";
		Node pointer = head;
		for(int i = 0; i < numAdded; i++)
		{
			if (i == numAdded-1)
			{
				x += Double.toString(pointer.getValue());
			}
			else
			{
				x += Double.toString(pointer.getValue()) + " ";
			}
			pointer = pointer.getNext();
		}
		return x;
	}
	
}
