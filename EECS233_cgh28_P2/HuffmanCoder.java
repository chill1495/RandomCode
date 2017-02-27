import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HuffmanCoder {
	
	public String input = " ", output = " ";
	
	public HuffmanCoder(String input, String output)
	{
		this.input = input;
		this.output = output;
	}
	
	public void compress()
	{
		try{
		BinaryWriter writer = new BinaryWriter(output);
		HuffmanCode code = new HuffmanCode(input);
		byte[] original;
		original = Files.readAllBytes(Paths.get(input));		//byte array of the original input
		boolean[] myArray;
		for(int i = 0; i < original.length; i++)
		{
			myArray = code.code(original[i]);			//get the boolean code for each byte
			writer.writeBinaryArray(myArray);			//output the code
		}
		writer.close();
		}
		catch(IOException e){ 
			e.printStackTrace();
		}
	}
}
