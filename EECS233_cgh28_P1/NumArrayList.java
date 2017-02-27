/* Coursin Hill
 * EECS 233 Project 1
 * 9/21/2015
 */
public class NumArrayList implements NumList {
	
	public double[] numArray;
	int numAdded;							//variable to know how many items are stored in the array
	
	public NumArrayList()
	{
		numArray = new double[0];
		numAdded = 0;
	}
	
	public int size()
	{
		return numArray.length;
	}
	
	public int capacity()
	{
		return numArray.length - numAdded;		//length - the amount added tells how many are positions are free
	}
	
	public void add(double value)
	{
		if (numArray.length == 0)					//if nothing is added, make a new slot and add the value
		{
			numArray = new double[1];
			numArray[0] = value;
		}
		else if (capacity() == 0)					// if there is no space left, make the list one space longer and add the item
		{
			double[]temp = new double [numArray.length + 1];
			for (int i= 0; i< numArray.length; i++)
			{
				temp[i] = numArray[i];
			}
			temp[numArray.length] = value;
			numArray = temp;
		}
		else
		{
			numArray[numArray.length - capacity()] = value;			//if there is space, just add at the end
		}
		
	numAdded++;						// item was added
		
	}
	
	public void insert(int i, double value)
	{	
		if (numAdded > i)				//case for the index being somewhere in the middle of the items added to the array
		{
			if (capacity() == 0)				//add a space if there is no space to insert an item
			{
				double[]temp = new double [numArray.length + 1];
				for (int k= 0; k< numArray.length; k++)
				{
					temp[k] = numArray[k];
				}
				numArray = temp;
			}
			for(int j = numAdded-1; j >= i; j--)	//move to the index, repositioning everything after the index, then add the item
			{
				numArray[j+1] = numArray [j];
			}
			numArray[i] = value;
			numAdded++;  		//item added
		}
		else if (numAdded < i)		//if the index of i is greater than the index of the last item, just add it to the end;
		{
			numArray[numArray.length - capacity()] = value;
		}
	}
	
	public void remove (int i)
	{
		if(numAdded > i)				//if the user is trying to remove a valid index
		{
			double[] temp = new double[numArray.length-1];			
			for (int j = 0; j< i; j++)				//move elements from 0 to (i-1) into new array
			{
				temp[j] = numArray[j];
			}
			for(int k = i+1; k < temp.length; k++)
			{
				temp[k-1] = numArray[k];			//move the elements from after i one position right
			}
			numArray = temp;
			numAdded--;				//item removed
		}
		
	}
	
	public boolean contains(double i)
	{
		for(int j = 0; j < numArray.length; j++)
		{
			if (numArray[j] == i)			//if found
			{
				return true;
			}
		}
		return false;						//not found
	}
	
	public double lookup(int i) throws IndexOutOfBoundsException
	{
		if (numArray.length > i)
		{
			return numArray[i];				//returns the value of the given index if valid
		}
		else
			throw new IndexOutOfBoundsException();  // exception if index is not valid
	}
	
	public boolean equals(NumList otherList)
	{
		if (otherList.size() != numArray.length)		// if the sizes are different, they aren't equal
			return false;
		for (int i = 0; i < otherList.size(); i ++)
		{
			if (otherList.lookup(i) != numArray[i])		//otherwise, check every index for equivalence
			{
				return false;
			}
		}
		return true;				//returns true if it passes the other tests
	}
	
	public void removeDuplicates()
	{
		for (int i = 0; i < numArray.length; i++)		//index of item to be compared
		{
			for (int j = i+1; j<numArray.length; j++)	//increment through the rest of the list
			{
				if (numArray[j] == numArray[i])			//if an index later in the list equals the compared item, remove it
				{
					remove(j);
				}
			}
		}
	}
	
	public String toString()
	{
		String result = "";
		for (int i = 0; i < numArray.length; i++)		//print all results followed by a space, unless they are the last item
		{
			if (i == numArray.length -1)
			{
				result += Double.toString(numArray[i]);
			}
			else
			{
				result += Double.toString(numArray[i]) + " " ;
			}
		}
		return result;
	}

}
