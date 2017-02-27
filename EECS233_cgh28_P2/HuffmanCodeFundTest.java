import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class HuffmanCodeFundTest {
	
	/* Other JUnit tests.
     * 
     * Write your own JUnit tests below to check the correctness of your implementation.
     * 
     * When you turn in your draft (and final) we will run our own test suite on your code 
     * and provide you with the feedback.
     * 
     * Your draft code should contain a complete set of methods as specified in the assignment.
     * Any methods not yet implemented should be written as skeleton methods with an empty body. 
     * 
	 * The JUnit tests below help to ensure that your methods compile with our test suite and have 
	 * correctly typed arguments. You can replace them with more meaningful tests to test their
	 * functionalities.
     */
	
	@Test
	public void testByteArrayArgumentConstructor() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'});
    	assertEquals("The constructor make a HuffmanCode using byte array",
			hc.toString(), "98:\t1\n97:\t0");
	}
	
	@Test
	public void testStringArgumentConstructor() throws IOException {
    	HuffmanCode hc = new HuffmanCode("file.txt");
    	assertEquals("The constructor make a HuffmanCode from a file",
    			"97:\t0\n98:\t11\n99:\t101\n10:\t100", hc.toString());
	}
	
	@Test
	public void testByteAndCountArraysConstructor() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b', (byte)'c'}, new int [] {2, 3, 2});
    	assertEquals("The constructor make a HuffmanCode using byte and count arrays",
    			"98:\t0\n99:\t11\n97:\t10", hc.toString());
	}
	
	@Test
	public void testCodeMethod() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    	boolean[] code = hc.code((byte)'a');
    	boolean[] code2 = hc.code((byte)'b');
    	boolean x [] = {false};
    	boolean y [] = {true};
    	assertArrayEquals("This method reurns the code of specific byte", x,
			code);
    	assertArrayEquals("Checking the second code", y, code2);
	}
	
	@Test
	public void testToStringMethod() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    	String s = hc.toString();
    	assertEquals("This method returns a string containing the table of the binary encodings of each byte in the Huffman tree",
			"98:\t1\n97:\t0", s);
	}
}