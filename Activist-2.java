import java.io.*;
import java.util.*;

public class Activist {

    static BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");
    
    int amountOfladies;
    double radius;
    ArrayList<Point> potentialBombLocation = new ArrayList<Point>();
    Point[] ladies;
    int results;


    public void getInput() throws Exception {
    	this.amountOfladies = readInt();
    	this.radius = readDouble();
    	this.ladies = new Point[amountOfladies];
    	this.results = 0;

    	for (int i = 0; i < amountOfladies; i++) {
			ladies[i] = new Point(readDouble(), readDouble());
		}
    	if ((this.amountOfladies == 0) && (this.radius == 0.0)) {
    		System.exit(0);
    	}
    	
    	for (int i = 0; i < ladies.length; i++) {
    		for (int j = 0; j < ladies.length; j++) {
    			if (ladies[i].equals(ladies[j])) {
    				continue;
    			}	
    			else {
    			getIntersections(ladies[i], ladies[j]);	
    			}
			}
		}
    }

    
    // Half of these are formulas from the internet which I've gone through at some point. 
    public void getIntersections(Point p1, Point p2) {	
    	
    	double distance = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    	double diameter = this.radius*2;

    	// Circles being too far apart. 
    	if (distance > diameter) {
    		return;
    	}
    	
    	else {
    		double a = (distance)/(2);
    		double h = Math.sqrt(this.radius*this.radius - a*a);
    		
    		
    		double x2 = p1.x + a * (p2.x - p1.x)/distance;
    		double y2 = p1.y + a * (p2.y - p1.y)/distance;
    		
    		Point p3 = new Point(x2+h*(p2.y - p1.y)/distance,y2 - h* (p2.x - p1.x)/ distance );
    		Point p4 = new Point(x2-h*(p2.x - p1.x)/distance,y2 +h* (p2.x - p1.x)/ distance );
    		

    		this.potentialBombLocation.add(p3); 
    		this.potentialBombLocation.add(p4); 
    		
    	}    	
    }
    
    
    public double getDistance(Point p1, Point p2) {
    	double distance = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    	
    	return distance;
    }
    
    public void getResults() {
    	int current = 0;
    	for (Point p : potentialBombLocation) {
    		for (int i = 0; i < ladies.length; i++) {
				if (getDistance(p, ladies[i]) <= radius) {
					//System.out.println("crash");
					//System.out.println("Bomb " + p.toString() + " Lady:" + ladies[i].toString());
					current++;
					//System.out.println("current: " + current);
				}
			}
    		if (current > results) {
    			//System.out.println(" current vs results " + current + " " + results);
    			results = current;
    		}
    		current = 0;	
		}
    }
    public static void main(String[] args) throws Exception {
        // Your code here.
    	while(true) {
    	Activist act = new Activist();
    	 act.getInput();
    	 if ((act.radius == 0.0) && (act.amountOfladies == 0)) {
    		 break;
    	 }
    	 act.getResults();
    	 System.out.println(act.results);
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
    		this.x = x;
    		this.y = y;
    	}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}			
    }    
}