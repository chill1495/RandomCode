import static org.junit.Assert.*;
import org.junit.Test;

public class HuffmanNodeFundTest {

	/* JUnit tests of fundamental functionality
     *
     * Use these JUnit tests to ensure that your code correctly
     * implements the fundamental functionality.
     */

	@Test
    public void testArrayArgumentConstructorAndToString() {
    	HuffmanNode hn = new HuffmanNode((byte)'a', 5);
    	assertEquals(
			"Your constructors should initialize the byte and count members based on the passed arguments.",
            (byte)'a', hn.b);
    	assertEquals(
			"Your constructors should initialize the byte and count members based on the passed arguments.",
            5, hn.count);
    	assertEquals(
			"Your constructors should initialize the byte and count members from the arguments. The left branch should be null.",
            null, hn.left);
        assertEquals(
			"Your constructors should initialize the byte and count members from the arguments. The right branch should be null.",
            null, hn.right);
    }

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

	// This test ensures that you make each member in HuffmanNode public.
	@Test
	public void testPublicMember() {
		HuffmanNode hn = new HuffmanNode((byte)'a', 5);
		hn.b = (byte)10;
		hn.count = 2;
		hn.left = null;
		hn.right = null;
		hn.code = new boolean[8];
		
		assertEquals("checking if they change individually", hn.b, (byte)10);
		assertEquals("count test", hn.count, 2);
		assertEquals("left test", hn.left, null);
		assertEquals("right test", hn.right, null);
		assertArrayEquals("code test", hn.code, new boolean[8]);
	}
}