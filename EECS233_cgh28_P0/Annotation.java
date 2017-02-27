
public class Annotation {

	private int n;
	
	public Annotation()
	{
		n = 0;
	}
	
	public Annotation(int num)
	{
		n = num;
	}
	
	public int getn()
	{
		return n;
	}
	
	public String toString()
	{
		if (n%3 == 0 && n%5 == 0)
		{
			return "FizzBuzz";
		}
		else if (n%3 == 0)
		{
			return "Fizz";
		}
		else if (n%5 == 0)
		{
			return "Buzz";
		}
		else
		{
			return String.valueOf(n);
		}
		
	}
	
	public static String annotateList(int start, int end)
	{
		String output = "";
		for (int i = start; i<= end; i++)
		{
			if (i == end)
			{
				Annotation num = new Annotation(i);
				output += num.toString();
			}
			else
			{
				Annotation num = new Annotation(i);
				output += num.toString() + " ";
			}
		}
		return output;
	}
	
}
