package jpkg.ngram;

import org.junit.Test;

import jpkg.test.TestManager;
import static org.junit.Assert.*;

public class NGramTest {
	public static void main(String[] args) {
		test();
		TestManager.runTests();
	}

	public static void test() {
		TestManager.addTest(NGramTest.class);
	}

	@Test
	public void testNGram() {
		assertArrayEquals(new String[] { "hell", "ello", "llo ", "lo t",
				"o th", " the", "ther", "here", "ere!" },
				NGram.getFromString("hello there!", 4));

		assertArrayEquals(new String[] { "hello" },
				NGram.getFromString("hello", 5));
	}

	@Test
	public void testEmptyNGram() {
		assertArrayEquals(new String[] {}, NGram.getFromString("hello!", 10));
	}

	@Test
	public void testNGramPairs() {
		assertArrayEquals(new String[] { "foo", "ba", "oob", "ar" },
				NGram.getPaired("foobar", 3, 2));
		assertArrayEquals(new String[] { "hell", "o ", "ello", " w", "llo ",
				"wo", "lo w", "or", "o wo", "rl", " wor", "ld" },
				NGram.getPaired("hello world", 4, 2));

	}
}
