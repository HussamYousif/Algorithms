import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.StringTokenizer;

/**
 * 
 * How this algorithm solved the:
 * 
 * 1. I read each number into a string array. 
 * 2. For each number, I created substrings. One substring for each successive character in the string and put it into the map. 
 * 3. Then I checked if the map contained any of the strings in the array. 
 * 
 * Example:
 * Given the strings 911 and 9112
 * String array: 911, 9112
 * Hashmap: (9,0), (91 (911 - the last character),0) and (911 (9112 - the last character), 0)
 * 
 *  Then for each string in string array (911, 9112)
 *  Does map have that key?
 *  Map does indeed have 911, which then proves that the phonebook is not consistent. 
 * 
 * @author Hussam
 *
 */
public class Phone {
	
	
	public static void main(String[] args) throws Exception {
		int tests = readInt();
		
		for(int i = 0; i < tests; i++) {
			int amountOfNumbers = readInt();
			String[] numbers = new String[amountOfNumbers];
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			
			for (int j = 0; j < amountOfNumbers; j++) {
				numbers[j] = readString();
			}
			
			for(int j = 0; j < amountOfNumbers; j++) {
				for (int j2 = 1; j2 < numbers[j].length(); j2++) {
					String partString = numbers[j].substring(0, j2);
					//System.out.println(partString);
					map.put(partString, 0);
				}
			}
			for (int j = 0; j < numbers.length; j++) {
				if (map.containsKey(numbers[j])) {
					System.out.println("NO");
					break;
				}
				if (j == numbers.length-1) {
					System.out.println("YES");
					break;
				}
			}	
		}
	}

	
	static BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in));
	static StringTokenizer st = new StringTokenizer("");
	// Read next input-token as string.
	static String readString() throws Exception {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(stdin.readLine());
		}
		return st.nextToken();
	}

	// Read next input-token as integer.
	static int readInt() throws Exception {
		return Integer.parseInt(readString());
	}

	// Read next input-token as double.
	static double readDouble() throws Exception {
		return Double.parseDouble(readString());
	}

}
