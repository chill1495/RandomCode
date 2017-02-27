import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class ByteCounter {
	
	private byte[] myArray;						//holds all the bytes from the input
	private int[] occurances = new int[256];	//an array to hold the count of any possible byte
	
	public ByteCounter(byte[] input)
	{
		myArray = input;
		for (int i = 0; i < input.length; i++)
		{
			occurances[input[i] + 128]++;
		}
		compile();
		setOrder("byte");						//order correctly after duplicates removed
	}
	
	public ByteCounter(String input)
	
	{		
			try {
				myArray = Files.readAllBytes(Paths.get(input));
				
				for(int i = 0; i < myArray.length; i++)
				{
					occurances[myArray[i] + 128]++;
				}
				
				
				compile();							//remove duplicates
				setOrder("byte");					//order correctly
			
			
			} catch (IOException e) {
				System.out.println("file " + input + " not found");
			}
	}
	
	private void compile()				//removes duplicates from myArray after they've been counted
	{
		for(int i =0; i< myArray.length; i++)
		{
			for (int j = i + 1; j < myArray.length; j++)
			{
					if (myArray[j] == myArray[i])
					{
						remove(j);
						j--;
					}
			}
		}
	}
	
	private void remove(int index)			//removes the element at the selected index
	{

		if(myArray.length > index)				
		{
			byte[] temp = new byte[myArray.length-1];		
			for (int j = 0; j< index; j++)				
			{
				temp[j] = myArray[j];
			}
			for(int k = index+1; k <= temp.length; k++)
			{
				temp[k-1] = myArray[k];			
			}
			myArray = temp;
		}
	}
	
	private byte[] sort(byte[] unSorted)			//quicksort method to sort the elements in byte order
	{
		byte x;
		int j;
		for (int i = 1; i < unSorted.length; i++)
		{
			x = unSorted[i];
			for (j = i; j > 0 && unSorted[j-1] > x; j--)
			{
				unSorted[j] = unSorted[j-1];
			}
			unSorted[j] = x;
		}
		return unSorted;
	}
	
	private byte[] insert(byte b, int index, byte[] array)		//insert a byte in the specified array at the given index
	{
			for (int j = array.length -2; j >= index; j--)	
			{
				array[j+1] = array[j];
			}
			array[index] = b;
			return array;
	}
	
	public int getCount(byte b)					//the byte is the index, like a hash table --> O(1)
	{
		return(occurances[b + 128]);
	}
	
	public int[] getCount(byte[] b)
	{
		int[] count = new int[b.length];
		for (int i = 0; i < b.length; i++)
		{
			count[i] = occurances[b[i] + 128];
		}
		return count;
	}
	
	public void setOrder(String order)
	{
		if (order.equalsIgnoreCase("byte"))		//use sort method if byte
		{
			myArray = sort(myArray);
		}
		else if (order.equalsIgnoreCase("countInc"))
		{
			byte[] temp = new byte[myArray.length];		//new array to hold order
			int position;							//position to place the byte in the new array
			for(int i = 0; i<myArray.length; i++)		//gets each element in the old array
			{
				position = 0;
				while(occurances[myArray[i] + 128] > occurances[temp[position] + 128] && temp[position] != 0)		//while the current byte has a higher count and you're not at an invalid index
				{
					position++;
				}
				while (occurances[myArray[i] + 128] == occurances[temp[position] + 128])		//while indices are equal
				{
					if(myArray[i] > temp[position]  && temp[position] != 0)		//sort by byte order
					{
						position++;
					}
					else
						break;
				}
				temp = insert(myArray[i], position, temp);					//insert at specified position
			}
			myArray = temp;
		}
		else if (order.equalsIgnoreCase("countDec"))		//same method as for countInc but with a < instead of a >
		{
			byte[] temp = new byte[myArray.length];
			int position;
			for(int i = 0; i<myArray.length; i++)
			{
				position = 0;
				while(occurances[myArray[i] + 128] < occurances[temp[position] + 128] && temp[position] != 0)
				{
					position++;
				}
				while (occurances[myArray[i] + 128] == occurances[temp[position] + 128])
				{
					if(myArray[i] > temp[position]  && temp[position] != 0)
					{
						position++;
					}
					else
						break;
				}
				temp = insert(myArray[i], position, temp);
			}
			myArray = temp;
		}
		else
			throw new IllegalArgumentException();
	}
	
	public byte[] getElements()			//because of the compile method, myArray is already what we need
	{
		return myArray;
	}
	
	public String toString()
	{
		String output = "";
		for (int i = 0; i < myArray.length; i++)
		{
			if (i == myArray.length - 1)
			{
				output += Integer.valueOf((int)myArray[i]) + ":" + Integer.valueOf(occurances[myArray[i] + 128]);
			}
			else
			{
			output += Integer.valueOf((int)myArray[i]) + ":" + Integer.valueOf(occurances[myArray[i] + 128]) + " ";
			}
		}
		return output;
	}
	
	public String toString(String format)		//same as other toString but with character cast
	{
		if (format.equals("byte"))
		{
			String output = "";
			for (int i = 0; i < myArray.length; i++)
			{
				if (i == myArray.length - 1)
				{
					output += Character.valueOf((char)myArray[i]) + ":" + Integer.valueOf(occurances[myArray[i] + 128]);
				}
				else
				{
				output += Character.valueOf((char)myArray[i]) + ":" + Integer.valueOf(occurances[myArray[i] + 128]) + " ";
				}
			}
			return output;	
		}
		return "";
	}
	
	
}
