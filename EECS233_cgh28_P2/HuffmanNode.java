/*
 *Basic HuffmanTreeNode class with constructors for when you have left and right branches and when you do not.
 *all other methods are just helper methods to get the data from the Nodes.
 */
public class HuffmanNode {
	
	public byte b = (byte)0;
	public int count = 0;
	public boolean[] code;
	public HuffmanNode left, right;

	public HuffmanNode(byte input, int count)
	{
		b = input;
		this.count = count;
		code = createBool(input);
		left = null;
		right = null;
	}
	public HuffmanNode(byte input, int count, HuffmanNode left, HuffmanNode right)
	{
		b = input;
		this.count = count;
		code = createBool(input);
		this.left = left;
		this.right = right;
	}
	
	private boolean[] createBool(byte b)
	{
		boolean[] bool = new boolean[8];
		bool[7] = ((b & 0x01) != 0);
		bool[6] = ((b & 0x02) != 0);
		bool[5] = ((b & 0x04) != 0);
		bool[4] = ((b & 0x08) != 0);
		bool[3] = ((b & 0x10) != 0);
		bool[2] = ((b & 0x20) != 0);
		bool[1] = ((b & 0x40) != 0);
		bool[0] = ((b & 0x80) != 0);
		return bool;
	}
}
