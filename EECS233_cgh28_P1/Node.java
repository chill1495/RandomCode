
public class Node {
	private double d;
	private Node next;
	
	public Node()
	{
		d = 0;
		next = null;
	}
	
	public Node (double value, Node y)
	{
		d = value;
		next = y;
	}
	
	public void setNext(Node q)
	{
		next = q;
	}
	
	public double getValue()
	{
		return d;
	}
	
	public Node getNext()
	{
		return next;

	}
}

