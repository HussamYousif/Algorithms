import java.io.*;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

public class Tv {
    static BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");
    
    int[][] shows;
    int amountOfShows;

    public void getInput() throws Exception {
    	amountOfShows = readInt();
    	shows = new int[amountOfShows][3];
    		
    	for (int i = 0; i < shows.length; i++) {
    		shows[i][0] = readInt();
    		shows[i][1] = readInt();
    		shows[i][2] = readInt();	
		}
    	
    	java.util.Arrays.sort(shows, new java.util.Comparator<int[]>() {
    	    public int compare(int[] a, int[] b) {
    	        return Double.compare(a[0]+a[1], b[0]+b[1]);
    	    }
    	});    	
    	//showArray();
	}
    
    public void showArray() {
    	for (int i = 0; i < shows.length; i++) {
			System.out.println("[" + shows[i][0] + "," + shows[i][1] + "," + shows[i][2] +"]");
		}
    }
    
    public int compute() {
    	int result = 0;
    	int longestShow = shows[amountOfShows-1][0]+shows[amountOfShows-1][1];
    	int[] schedule = new int[longestShow+1];
    	int bound = 0;

    	 for (int i = 0; i < shows.length; i++) {
			int start = shows[i][0];
			int end = shows[i][0] + shows[i][1];
			int point = shows[i][2];

			schedule[end] = max(schedule[end], schedule[start] + point);
			result = schedule[end];
			try {
				bound = shows[i+1][0] + shows[i+1][1];
			}
			catch (Exception e) {
				bound = schedule.length-2;
			}
			for (int j = end; j < bound+1; j++) {
				schedule[j] = result;
			}
		}
    	return schedule[schedule.length-1];
    }
    
    public int max(int a, int b) {
    	if (a >= b) {
    		return a;
    	}
    	else 
    		return b;
    }
    
    public static void main(String[] args) throws Exception {
    	int testCases = readInt();
    	for (int i = 0; i < testCases; i++) {
			
    		Tv tv = new Tv();
    		tv.getInput();
    		System.out.println(tv.compute());
		}
    	
    }

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
