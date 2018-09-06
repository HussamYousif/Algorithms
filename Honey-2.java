import java.io.*;
import java.util.*;

public class Honey {
    static BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws Exception {
    	int testCases = readInt();
    	for (int i = 0; i < testCases; i++) {
			int moves = readInt();
    		hue(moves);
		}

    }
    
    public static void hue(int movement) {
    	int[][][] grid = new int[17][17][17];
    	grid[8][8][0] = 1;
    	
    	for(int move = 0; move < 14; move++) {
    		for (int x = 1; x < 16; x++) {
				for (int y = 1; y < 16; y++) {
					grid[x][y][move+1] += grid[x+1][y][move];
					grid[x][y][move+1] += grid[x-1][y][move];
					grid[x][y][move+1] += grid[x+1][y+1][move];
					grid[x][y][move+1] += grid[x-1][y-1][move];
					grid[x][y][move+1] += grid[x][y+1][move];
					grid[x][y][move+1] += grid[x][y-1][move];
				}
			}
    	}
    	
    	System.out.println(grid[8][8][movement]);
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