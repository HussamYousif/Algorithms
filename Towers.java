import java.io.*;
import java.util.*;

public class Towers {
    static BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");
 
    
    public static void main(String[] args) throws Exception {
    	
    	while (true) {
    		int amountOfFloors = readInt();
    		if (amountOfFloors == 0) {
    			break;
    		}
    		int[] floors = new int[amountOfFloors];
    		
    		if (amountOfFloors == 0) {
    			System.exit(0);
    		}
    		for (int i = 0; i < amountOfFloors; i++) {
				floors[i] = readInt();
			}
    		
    		while(true) {
    			int beboere = 0;
    			String command = readString();

    			if (command.equals("S")) {
    				System.out.println("Slutt for i dag.");
    				break;
    			}

    			int a = readInt();
    			int b = readInt();
				}*/

    			
    			if (command.equals("I")) {
    				// Increase
    				floors[a-1] += b;
				}
    			if (command.equals("U")) {
    				// decrease
    				floors[a-1] -= b;
    			}
    			if (command.equals("T")) {
    				// Count
    				for (int i = a-1 ; i < b; i++) {
						beboere += floors[i];
					}
    				if (beboere == 1 && a == b) {
    					System.out.println("Det er 1 gjest som bor i etasje " + a + ".");
    				}
    				else if (beboere == 1) {
    					System.out.println("Det er 1 gjest i som bor mellom etasje " + a + " og etasje " + b  + "." );
    				}
    				else if (a == b) {
    					System.out.println("Det er " + beboere + " gjester som bor i etasje " + a + ".");
    				}
    				
    				else if (a-1 == 0 && b == floors.length) {
    					System.out.printf("Det er %d gjester som bor i hotellet.\n", beboere);
    				}
    				else {
    					System.out.println("Det er " + beboere + " gjester som bor mellom etasje " + a + " og etasje " + b + ".");
    				}
    			}
    		}
    		
    	
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
