import java.io.*;
import java.util.*;

public class Fish {
	static BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in));
	static StringTokenizer st = new StringTokenizer("");

	long[][] towns;
	int numberOfTowns;
	long upperBound;
	long lowerBound;

	public Fish() throws Exception {
		upperBound = 0;
		numberOfTowns = readInt();
		towns = new long[numberOfTowns][2];
		for (int i = 0; i < numberOfTowns; i++) {
			towns[i][0] = readInt();
			towns[i][1] = readInt();
			upperBound += towns[i][1];

			if(i == 0)
				lowerBound = towns[i][1];

			if (this.towns[i][1] < lowerBound) {
				lowerBound = towns[i][1];
			}
		}
		upperBound /= numberOfTowns;
	}

	public long compute() {
		boolean hue = false;
		long currentTown = towns[0][1];
		long nextTown = 1;
		long prevAnswer = 0;
		long results = (upperBound+lowerBound)/2;

		while(true) {
			currentTown = towns[0][1];

			for (int i = 0; i < numberOfTowns-1; i++) {
				nextTown = towns[i+1][1];
				long distance = towns[i+1][0] - towns[i][0];

				// Get fish from next town.
				if (currentTown < results) {
					nextTown = nextTown - (results - currentTown) - distance;
					currentTown = results;
				}

				// Recieve fish to nextTown.
				if (currentTown > results)   {
					long rest = currentTown - results - distance;
					if (rest > 0) {
						nextTown += rest ;
					}
					currentTown = results;
				}
				currentTown = nextTown;

			}
			if (results == prevAnswer) {
				return results;
			}
			prevAnswer = results;

			if (results == currentTown) {
				return results;
			}
			if (currentTown > results)
				lowerBound = results+1;

			if(currentTown < results)
				upperBound = results-1;


			results = (upperBound + lowerBound)/2;

		}
	}

	public static void main(String[] args) throws Exception {
		Fish fish = new Fish();
		System.out.println(fish.compute());
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