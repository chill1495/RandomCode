
public class NumSet {
	
	NumList x;
	
	public NumSet(NumList values)
	{
		x = values;
	}

	public int size()
	{
		return x.size();
	}
	
	public boolean contains(double value)
	{
		return x.contains(value);
	}
	
	public double lookup(int i)
	{
		return x.lookup(i);
	}
	
	public static NumSet intersect (NumSet S1, NumSet S2)
	{
		NumList temp = new NumLinkedList();
		for (int i = 0; i < S1.size(); i++)
		{
			temp.add(S1.lookup(i));
		}
		for (int j = 0; j < S2.size(); j++)
		{
			temp.add(S2.lookup(j));
		}
		temp.removeDuplicates();
		NumSet finished = new NumSet(temp);
		return finished;
	}
	
	public static NumSet union (NumSet S1, NumSet S2)
	{
		NumList temp = new NumLinkedList();
		int count1 = 0, count2 = 0;
		for (int i = 0; i < S1.size() + S2.size(); i++)
		{
			if (count1 < S1.size() && count2 < S2.size())
			{
				if (i%2 == 0)
				{
					if (count1 < S1.size())
					{
						temp.add(S1.lookup(count1));
					}
					count1++;
				}
				else
				{
					if (count2 < S2.size())
					{
						temp.add(S2.lookup(count2));
					}
					count2++;
				}
			}
			else if (count1 < S1.size())
			{
				temp.add(S1.lookup(count1));
				count1++;
			}
			else if (count2 < S2.size())
			{
				temp.add(S2.lookup(count2));
				count2++;
			}
		}
		temp.removeDuplicates();
		NumSet finished = new NumSet(temp);
		return finished;
	}
	
	public static boolean equivalence(NumSet S1, NumSet S2)			
	{
		if (S1.size() != S2.size())
			return false;
		else
		{
			for (int i = 0; i < S1.size(); i++)
			{
				if (!S2.contains(S1.lookup(i)))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public String toString()
	{
		return x.toString();
	}
}
