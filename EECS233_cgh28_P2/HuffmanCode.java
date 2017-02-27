import java.io.IOException;
import java.util.LinkedList;

public class HuffmanCode {
	
	private LinkedList<HuffmanNode> LL = new LinkedList<HuffmanNode>();
	private byte[] myArray;
	private ByteCounter bc;
	
	public HuffmanCode(byte[] b)
	{
		bc = new ByteCounter(b);
		bc.setOrder("countInc");
		byte[] elements = bc.getElements();			//holds all the individual bytes
		myArray = elements;
		for(int i = 0; i < elements.length; i++)
		{
			LL.add(new HuffmanNode(elements[i], bc.getCount(elements[i])));
		}
		compileTree();				//method to create the huffmanTree
	}
	
	public HuffmanCode(String input)
	{
		bc = new ByteCounter(input);		//only change from first constructor
		bc.setOrder("countInc");
		byte[] elements = bc.getElements();
		myArray = elements;
		for (int i = 0; i < elements.length; i++)
		{
			LL.add(new HuffmanNode(elements[i], bc.getCount(elements[i])));
		}
		compileTree();
	}
	
	public HuffmanCode(byte[] b, int[] counts)
	{
		byte[] orderedByte = new byte[b.length];			
		int[] orderedInt = new int[counts.length];			//lists to hold b and counts in the correct order
		
		for (int i = 0; i < b.length; i++)				//duplicate bytes check
		{
			for (int j = i+1; j< b.length; j++)
			{
				if (b[i] == b[j])
					throw new IllegalArgumentException();
			}
		}
		for (int i = 0; i < counts.length; i++)			//negative counts check
		{
			if (counts[i] < 0)
				throw new IllegalArgumentException();
		}
		
		int count;
		for (int i = 0; i < counts.length; i++)			//goes through each byte/int and compares to the ones currently in orderedByte/Int and places it in the correct spot
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
		
		for (int i = 0; i < counts.length; i++)			//creating list
		{
			LL.add(new HuffmanNode(orderedByte[i], orderedInt[i]));
		}
		myArray = orderedByte;
		compileTree();				//create tree
	}
	
	private void compileTree()
	{
		while(LL.size() > 1)		//while there are still nodes to merge
		{
			HuffmanNode node = new HuffmanNode((byte)0, LL.get(0).count + LL.get(1).count, LL.get(0), LL.get(1));	//create new huffmanNode with the combined counts and the correct left and right nodes
			int index = 0;
			while((index < LL.size()) && (node.count > LL.get(index).count))	//find where to add the new node
			{
				index++;
			}
			LL.add(index, node);
			LL.removeFirst();		//remove the two merged nodes
			LL.removeFirst();
			
		}
	}
	
	public boolean[] code(byte b)
	{
		String code = "";
		code = getCode(b, LL.get(0), code);	//string version of the code
		byte x;
		boolean[] bool = new boolean[code.length()];
		for (int i = 0; i < bool.length; i++)	
		{
			x = Byte.valueOf(code.substring(i, i+1)); 
			bool[i] = ((x & 1) == 1);		
		}
		return bool;
	}
	
	public String codeString(byte b)
	{
		HuffmanNode node = LL.get(0);
		String code = "";
		code = getCode(b, node, code);
		if (code.equals("ERROR"))			//if element doesn't exist
		{
			throw new IllegalArgumentException();
		}
		else
		{
			return code;
		}
	}
	
	private String getCode(byte b, HuffmanNode hn, String path) 	//recursive method to find the path to a certain node
	{
		String code = "ERROR";
		if (b == hn.b)
			code = path;
		else if (hn.left != null || hn.right != null)
		{
			if ((code = getCode(b, hn.left, path+ "0")) == null)	//if it isnt' in the left path, go to the right path
			{
				code = getCode(b,hn.right, path + "1");
			}
		}
		else
		{
			 if (b == hn.b)
			 {
				 code = path;
			 }
			 else			//if it doesn't exist
			 {
				 code = null;
			 }
		}
		return code;	
	}
	
	
	public String toString()
	{
		String x = "";
		String[] y = new String[myArray.length];		//array to hold individual strings
		int count;
		byte[] z = new byte[myArray.length];			//new placements for bytes
		for (int i = 0; i < myArray.length; i++)
		{
			x = codeString(myArray[i]);				//get the code for the string
			count = 0;
			while(y[count] != null && (codeString(myArray[i]).length() > y[count].length()))	//find position based on string length
			{
				count++;
			}
			while(y[count] != null && (codeString(myArray[i]).length() == y[count].length()))
			{
				if(myArray[i] < z[count])		//find position based on byte when lengths are equal
				{
					count++;
				}
				else
					break;
			}
			insert(x, count, y);			//insert string at that position
			insert(myArray[i], count, z);	//insert byte into new array
		}
		x = "";
		for(int i = 0; i < y.length; i++)
		{
			if (i == y.length -1)
			{
				x += z[i] + ": " + y[i];
			}
			else
			{
				x += z[i] + ": " + y[i] + "\n";			//combine into single string for return
			}
		}
		
		return x;
	}
	
	private String[] insert(String input, int index, String[] array)	//insert used for a string
	{
			for (int j = array.length -2; j >= index; j--)	
			{
				array[j+1] = array[j];
			}
			array[index] = input;
			return array;
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

