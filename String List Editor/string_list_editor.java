import java.util.Arrays;

public class MIM_Code {
	
	public static void main(String[] args)
	{
		String[] strings = {"HelloThere", "name", "YELling","hyphen-","ate"};	//sample input with all different cases
		string_list_editor(strings);
	}
	
	public static void string_list_editor(String[] strings)
	{
		int start_chars = 0, end_chars = 0, median = 0;
		start_chars = count_chars(strings);		
		
		int[] lengths = length_array(strings);	
		if(lengths.length % 2 == 0)			//calculate median
			median = (lengths[lengths.length / 2] + lengths[(lengths.length/2)-1]) / 2;
		else
			median = lengths[lengths.length / 2];
		
		if (strings.length > 0)			//check for valid inputs
		{
			for (int i = 0; i < strings.length; i++)
			{
				if(strings[i].length() % 4 == 0)	//length divisible by 4
				{
					String new_string = "";
					for (int j = strings[i].length() - 1; j >= 0; j--)
					{
						new_string += strings[i].charAt(j);
					}
					strings[i] = new_string;
				}
//*****************************************************************************************				
	
				if(strings[i].length() % 5 == 0)		//length divisible by 5
				{
					strings[i] = strings[i].substring(0,5);
				}
//*****************************************************************************************	
				
				int count = 0;			//check for capital letters
				for(int k = 0; k < strings[i].length(); k++)
				{
					if (k == 5)
					{
						break;
					}
					if (Character.isUpperCase(strings[i].charAt(k)))
					{
						count++;
					}
				}
				if (count >= 3)		//if 3 capitals, capitalize word
				{
					strings[i] = strings[i].toUpperCase();
				}
//*******************************************************************************************	
				
				if(strings[i].charAt(strings[i].length() - 1) == '-') //check for ending hyphen
				{
					if(i != strings.length - 1)
					{
						strings[i] = strings[i].substring(0,strings[i].length() - 1) + strings[i+1];
					}
					else
					{
						strings[i] = strings[i].substring(0,strings[i].length() - 1);
					}
				}
//*******************************************************************************************
				
				System.out.println(strings[i]);
			}
			end_chars = count_chars(strings);
			System.out.println("Starting chars: " + start_chars + "\nEnding chars: " + end_chars + "\nMedian: " + median);
		}
	}
	
	public static int count_chars(String[] words)		//counts chars in list
	{
		int num = 0;
		for (int index = 0; index < words.length; index++)
		{
			num += words[index].length();
		}
		return num;
	}
	
	public static int[] length_array(String[] words)	//returns a list of the lengths
	{
		int[] nums = new int[words.length];
		for(int i = 0; i < words.length; i++)
		{
			nums[i] = words[i].length();
		}
		Arrays.sort(nums);
		return nums;
	}
}
