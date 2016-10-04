package jpkg.ngram;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class NGram {
	// This class shouldn't be instiantated
	private NGram() { throw new AssertionError(); }
	
	/**
	 * This method gets ngrams from a provided string with specified length. If the 
	 * length is longer than the string itself, this method returns and empty array. 
	 * 
	 * @param str String to get ngrams for
	 * @param len Length of each substring
	 * @return An array of the ngrams. Each element is guaranteed to be exactly the length of len. 
	 */
	public static String[] getFromString(String str, final int len) {
		// If length is less than 1, throw exception
		if(len <= 0)
			throw new IllegalArgumentException("Length cannot be less than 1!");
		
		// If string length is less than specified length, return empty-handed
		if(str.length() < len)
			return new String[0];
		
		int num = str.length() - len + 1;
		
		String[] ret = new String[num];
		
		// Use char array because it's faster than substring
		char[] ch = str.toCharArray();
		
		for(int i = 0; i < num; i++) {
			ret[i] = new String(ch, i, len);
		}
		
		return ret;
	}
	
	/**
	 * Run a procedure for each String in the given stream
	 * 
	 * @param r Stream to use
	 * @param c Procedure to run
	 * @param len Length of ngram
	 */
	public static void runForEach(Stream<String> r, Consumer<String[]> c, int len) {
		r.forEach((str) -> { c.accept(getFromString(str, len)); });
	}
	
	/**
	 * This method gets pairs of ngrams from a provided string with specified lengths. If the 
	 * length is longer than the string itself, this method returns and empty array. 
	 * 
	 * For example, given an example string `foobar` and lengths of 3 and 2, the reuslt should be the following: 
	 * ["foo", "ba", "oob", "ar"]
	 * 
	 * @param str String to ngramify
	 * @param lena Length of ngram a
	 * @param lenb Length of ngram b
	 */
	public static String[] getPaired(String str, int lena, int lenb) {
		// If length is less than 1, throw exception
		if(lena <= 0 || lenb <= 0)
			throw new IllegalArgumentException("Length cannot be less than 1!");
		
		// If string length is less than specified length, return empty-handed
		if(str.length() < lena + lenb)
			return new String[0];
		
		int num = 2 * (str.length() - lena - lenb + 1);
		
		String[] ret = new String[num];
		
		// Use char array because it's faster than substring
		char[] ch = str.toCharArray();
		
		for(int i = 0; i < num; i += 2) {
			ret[i] = new String(ch, i / 2, lena);
			ret[i + 1] = new String(ch, i / 2 + lena, lenb);
		}
		
		return ret;
	}
	
	/**
	 * Run a procedure for each String in the given stream
	 * 
	 * @param r Stream to use
	 * @param c Procedure to run
	 * @param len Length of ngram
	 */
	public static void runPairForEach(Stream<String> r, Consumer<String[]> c, int lena, int lenb) {
		r.forEach((str) -> { c.accept(getPaired(str, lena, lenb)); });
	}
}
