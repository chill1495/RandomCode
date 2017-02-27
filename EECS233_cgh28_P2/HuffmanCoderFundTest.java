import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanCoderFundTest {

	/* Other JUnit tests.
     *
     * Write your own JUnit tests below to check the correctness of your implementation.
     * Leave the above methods intact and add your own tests below this comment so it's
     * easier for the graders to see what you've added.
     *
     * When you turn in your drafts we will run our own test suite on your code and provide
     * you with the feedback to help you debug an issues.
     *
     * Your draft code needs to contain a complete set of methods as specified in the assignment.
     * Otherwise, it won't compile with our test suite.  To avoid this, you should first write
     * a complete set of "skeleton" methods, i.e. methods with the correct names and
     * the correct return and argument types.
     *
     * To help ensure your code will compile when you submit it for testing, below we have
	 * included a set of skeleton tests. You should replace these with more meaningful tests
	 * as you write your methods.
     */

	@Test
	public void testConstructor() throws IOException {
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "file2.txt");
    	assertTrue(
			"The constructor makes a HuffmanCode using a 'file.txt'.", true);			//my constructor only sets the input and output the the files so there isn't anything to test
	}

	@Test
	public void testCompressMethod() throws IOException {
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "file2.txt");
    	hc.compress();
    	
    	ByteCounter bc = new ByteCounter("file2.txt");
    	byte x [] = {(byte)-73, (byte) -16, (byte) 1};				//calculated from writing out the values from the huffman code (ordered in byte order)
    	byte[] y = bc.getElements();
    	
    	assertArrayEquals("these should be the output bytes from the huffman code", x, y);
	}
}