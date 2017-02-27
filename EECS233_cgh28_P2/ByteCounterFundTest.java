import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class  ByteCounterFundTest {

    /* JUnit tests of fundamental functionality
     *   
     * Use these JUnit tests to ensure that your code correctly 
     * implements the fundamental functionality.
     */

    @Test
    public void testArrayArgumentConstructorAndToString() {
    	byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
    	ByteCounter byteCount = new ByteCounter(test);
        assertEquals(
            "A constructor with an byte array argument should store the unique bytes with their occurrences values. (This could also indicate a problem the the to String method.)",
            (byte)'a'+":3 "+ (byte)'b'+":2 "+(byte)'c'+":1", byteCount.toString());
    }
    
    @Test
    public void testStringArgumentConstructorAndGetCount()throws IOException {
		ByteCounter byteCount = new ByteCounter("C:\\Users\\cours_000\\Data Structures\\file.txt");
    	byte b = (byte)'a';
        assertEquals(
            "A constructor with a String arguemnt should compute the byte counts from the specified file. (This could also indicate a problem the the to String method.)",
            4, byteCount.getCount(b));
    }
    
    @Test
    public void testByteArgumentGetCount() {
    	byte[] test = {(byte)'y', (byte)'y', (byte)'z'};
    	byte b = (byte)'y';
    	ByteCounter byteCount = new ByteCounter(test);
        assertEquals(
            "The getCount method should take a byte value and return the number of occurrences.",
            2, byteCount.getCount(b));
    }
    
    @Test
    public void testArrayArgumentGetCount() {
    	byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
    	byte[]    b = {(byte)'c', (byte)'b', (byte)'a'};
    	ByteCounter byteCount = new ByteCounter(test);
    	int[] count = byteCount.getCount(b);
        assertArrayEquals(
            "The getCount method should take a byte array and return an array of the number of occurrences of each byte.",
            new int[] {1, 2, 3}, count);
    }
    
    @Test
    public void testGetElements() {
        byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
       	ByteCounter byteCount = new ByteCounter(test);
    	byte[] elements = byteCount.getElements();
        assertArrayEquals(
            "The getElements method should return an array of bytes that have a non-zero count.", new byte[] {(byte)'a', (byte)'b',
            (byte)'c'}, elements);
        assertEquals(
            "The getElements method should return an array of bytes that have a non-zero count", 3, elements.length);
    }
    
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
	public void testSetOrder() {
    	byte test [] = {(byte)'x', (byte)'c', (byte)'d'};
    	byte x [] = {(byte) 'c', (byte)'d', (byte)'x'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.setOrder("byte");
    	assertArrayEquals("Method setOrder define the order of the current object",test,x);
    	
    	byte test2 []= {(byte)'a', (byte)'b', (byte)'a'};
    	byte y [] = {(byte)'b', (byte)'a'};
    	byte z [] = {(byte)'a', (byte)'b'};
    	ByteCounter bc = new ByteCounter(test2);
    	bc.setOrder("countInc");
    	
    	assertArrayEquals("Set order of increasing count", bc.getElements(), y);
    	
    	bc.setOrder("countDec");
    	z = bc.getElements();
    	
    	assertArrayEquals("set order of decreasing count", bc.getElements(), z);
    	}
    
    @Test
	public void testFormatToString() {
    	byte test [] = {(byte)'a', (byte)'b'};
    	ByteCounter byteCount = new ByteCounter(test);
    	assertEquals("Method toString returns the bytes and their counts based on the format provided",
			byteCount.toString("byte"), "a:1 b:1");
    	assertEquals("Method toString returns the byte value and their counts", byteCount.toString(),
    			"97:1 98:1");
    	
    	byte test2 [] = {(byte)'a', (byte)'b', (byte) 'b', (byte)'a', (byte)'c'};
    	ByteCounter bc = new ByteCounter(test2);
    	
    	assertEquals("ByteCounter should be able to handle multiples of inputs", bc.toString("byte"),
    			"a:2 b:2 c:1");
	}
    
    @Test
    public void testGetCount()
    {
    	byte test [] = {(byte)'a', (byte)'b'};
    	int counts [] = {1,1};
    	
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.setOrder("byte");
    	assertArrayEquals("counts for the array of inputs", counts, byteCount.getCount(test));
    }
}