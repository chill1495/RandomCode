import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class HuffmanList {

	private LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
	
	public HuffmanList(byte[] b)
	{
		ByteCounter bc = new ByteCounter(b);
		bc.setOrder("countInc");
		byte[] elements = bc.getElements();
		
		for(int i = 0; i < elements.length; i++)
		{
			list.add(new HuffmanNode(elements[i], bc.getCount(elements[i])));
		}
	}
	
	public HuffmanList(String input)
	{
		ByteCounter bc = new ByteCounter(input);
		bc.setOrder("countInc");
		byte[] elements = bc.getElements();
		
		for (int i = 0; i < elements.length; i++)
		{
			list.add(new HuffmanNode(elements[i], bc.getCount(elements[i])));
		}
	}
	
	public HuffmanList(byte[] b, int[] counts)
	{
		byte[] orderedByte = new byte[b.length];
		int[] orderedInt = new int[counts.length];
		
		if(b.length != counts.length)				//check for different lengths
		{
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < b.length; i++)			//check for repeats
		{
			for (int j = i+1; j< b.length; j++)
			{
				if (b[i] == b[j])
					throw new IllegalArgumentException();
			}
		}
		for (int i = 0; i < counts.length; i++)		//check for negatives
		{
			if (counts[i] < 0)
				throw new IllegalArgumentException();
		}
		int count;
		for (int i = 0; i < counts.length; i++)			//order the arrays
		{
			count = 0;
			while(orderedInt[count] != 0 && counts[i] > orderedInt[count])
			{
				count ++;
			}
			while(orderedInt[count] != 0 && orderedInt[count] == counts[i])
			{
				if (b[i] > orderedByte[count])
				{
					count++;
				}
				else
					break;
			}
			orderedByte = insert(b[i], count, orderedByte);
			orderedInt = insert(counts[i], count, orderedInt);
		}
		
		for (int i = 0; i < counts.length; i++)
		{
			list.add(new HuffmanNode(orderedByte[i], orderedInt[i]));
		}
	}
	
	public java.util.Iterator<HuffmanNode> iterator()
	{
		java.util.Iterator<HuffmanNode> iter = list.iterator();
		return iter;
	}
	
	private byte[] insert(byte input, int index, byte[] array)			//insert method for bytes
	{
		for (int j = array.length -2; j >= index; j--)	
		{
			array[j+1] = array[j];
		}
		array[index] = input;
		return array;
	}
	
	private int[] insert(int input, int index, int[] array)
	{
		for (int j = array.length -2; j >= index; j--)	
		{
			array[j+1] = array[j];
		}
		array[index] = input;
		return array;	
	}
}



