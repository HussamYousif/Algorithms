import java.util.ArrayList;
import java.util.Scanner;


public class Outlet {

	static int testCases;
	static int[] availableOutletsPerStrip;
	static int powerStrips;
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int testCases = input.nextInt();
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		for(int i= 0; i < testCases; i++) {
		powerStrips = input.nextInt();
		availableOutletsPerStrip = new int[powerStrips];
		
		for (int j = 0; j < availableOutletsPerStrip.length; j++) {
			availableOutletsPerStrip[j] = input.nextInt();
		}
		
		results.add(calculate(powerStrips, availableOutletsPerStrip));
		
		
		}
		
		for(Integer result: results) {
			int res = result;
			System.out.println(res);
		}
		
		
	}
	public static Integer calculate(int powerStrips, int[] availableOutletsPerStrip) {
		
		Integer result = 0;
		
		for (int i = 0; i < availableOutletsPerStrip.length; i++) {
			result += availableOutletsPerStrip[i]-1;
		}
		return (result+1);
	}
	
}
