import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Field {
    static BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");
    
    public static void main(String[] args) throws Exception {
    	int testCases = readInt();
    	
    	for (int i = 0; i < testCases; i++) {
			
    	double[] p1 = {readDouble(), readDouble()};
    	double[] p2 = {readDouble(), readDouble()};
    	double[] p3 = {readDouble(), readDouble()};
    	
    	double BAx = p2[0] - p1[0]; 
    	double BAy = p2[1] - p1[1];
    	
    	double CAx = p3[0] - p1[0];
    	double CAy = p3[1] - p1[1];
    	
    	double axb = BAx * CAy - BAy*CAx;
    	double value = (Math.abs(0.5*axb));
    	DecimalFormat df = new DecimalFormat("0.000");
    	System.out.println(df.format(value));	
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
    
    public class Point {
    	
    	double x;
    	double y;
    	
    	
		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}


		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
    	
    	
    }
    
}
