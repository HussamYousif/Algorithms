import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.util.*;


public class Rafting {
	
	public static double distance(Coords first, Coords second) {
		return Math.sqrt(Math.pow(second.x - first.x, 2) + Math.pow(second.y + first.y, 2));
	}
	
	public static Coords subtract(Coords first, Coords second) {
		int x = first.x - second.x;
		int y = first.y - second.y;
		
		return new Coords(x,y);
	}
	
	public static int multiply(Coords first, Coords second) {
		return first.x*second.x+first.y*second.y;
	}
	
	public static double getDistance(Coords first, Coords second, Coords third) {
		
		if (multiply(subtract(first,second), subtract(third, second)) < 0) {
			double distance = multiply(subtract(first, second), subtract(first, second));
			return Math.sqrt(distance);
		}
		
		if (multiply(subtract(first, third), subtract(second, third)) < 0) {
			double distance = multiply(subtract(first, third), subtract(first, third));
			return Math.sqrt(distance);
		}
		
		Coords p1 = subtract(first, second);
		Coords p2 = subtract(third, second);
		
		double teller = p1.x*p2.y-p1.y*p2.x;
		teller = Math.abs(teller);
		double nevner = multiply(subtract(third, second), subtract(third, second));
		nevner = Math.sqrt(nevner);
		
		return teller/nevner;
		
		
	}
	
	public static void main(String[] args) throws Exception {
		int tests = readInt();
		
		for (int i = 0; i < tests; i++) {
			
			int innerPoly = readInt();
			Coords[] inner = new Coords[innerPoly];
			
			for (int j = 0; j < innerPoly; j++) {
				int x = readInt();
				int y = readInt();
				inner[j] = new Coords(x, y);	
			}
			
			int outerPoly = readInt();
			Coords[] outer = new Coords[outerPoly];
			
			for (int j = 0; j < outer.length; j++) {
				outer[j] = new Coords(readInt(), readInt());
			}
			
			
			double results = 10000000;
			for (int j = 0; j < innerPoly; j++) {
				for (int k = 0; k < outerPoly; k++) {
					results = min(results, getDistance(inner[j], outer[k], outer[(k+1)%outerPoly]));
					results = min(results, getDistance(outer[k], inner[j], inner[(j+1)%innerPoly]));
				}
			}	
			System.out.println(results/2);
		}
	}
	
	public static void showArray(Coords[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i].toString());
		}
	}
	
	private static double min(double a, double b) {
		if (a < b) 
			return a;
		else
			return b;
	}

	public static class Coords {
		int x,y;

		public Coords(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Coords [x=" + x + ", y=" + y + "]";
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