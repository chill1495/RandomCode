

public interface NumList {
	public int size();
	public void add(double value);
	public void insert (int i, double value);
	public void remove (int i);
	public boolean contains(double value);
	public double lookup (int i);
	public boolean equals (NumList otherList);
	public void removeDuplicates();
	public String toString();
}
