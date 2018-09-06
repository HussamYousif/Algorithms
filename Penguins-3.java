import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/**
 * Idea is simple, create a dummy source to connect to all the other nodes. Then try the piece nodes as sink till you find the correct ones and .
 * 
 * The runtime was around 14 sec on the server. I think I have a couple of ideas to bring the runtime down drastically. 
 * 
 * First one check only (as sinks) nodes that has edges with enough capacity to carry all penguins over to them. Can decrease runtime drastically.   
 * @author hussam
 *
 */
public class Penguins {

	int penguins; // Total amount of penguins will be stored here. 
	double maxJump;
	int pieces;
	int[][] pieceInf;
	int[][] graph; // This will run the actual algorithm. 
	boolean visited[];
	ArrayList<Integer> results = new ArrayList<Integer>();


	public void getInput() throws Exception {
		pieces = readInt();
		maxJump = readDouble();
		pieceInf = new int[pieces][4]; // the order is x, y, the penguins on the piece, the amount of times you can jump off.   

		for (int i = 0; i < pieces; i++) {
			pieceInf[i][0] = readInt();
			pieceInf[i][1] = readInt();
			pieceInf[i][2] = readInt();
			pieceInf[i][3] = readInt(); 
		}
		constructGraph();
	}

	public void constructGraph() {
		int[][] cap = new int[2*pieces+1][2*pieces+1];

		penguins=0;
		for(int i=0; i<pieces; i++) {
			penguins += pieceInf[i][2]; 
			cap[2*pieces][i]=pieceInf[i][2]; // Penguins on piece.
			cap[i][i+pieces]=pieceInf[i][3]; // Times you can jump off. 
			
			for(int j=0; j<pieces; j++) {
				if (i == j)
					continue;
				if(getDistance(i,j) <= maxJump) 
					cap[i+pieces][j]=Integer.MAX_VALUE;
			}
		}

		compute(cap);
	}
	
	public void compute(int[][] cap) {
		for (int i = 0; i < pieces; i++) {
			if (maxFlow(pieces*2, i, cap) == penguins) {
				results.add(i);
			}
		}
	}

	int maxFlow(int s, int sink, int[][] caps) {
		int flow=0;
		int path = -1;
		int capsSize=caps.length;
		graph = new int[capsSize][capsSize];
		
		
		for(int i=0; i<capsSize; i++) 
			for(int j=0; j<capsSize; j++) 
				graph[i][j]=caps[i][j]; // We deep copy.

		while (path != 0) {
			visited = new boolean[capsSize];
			 path = dfs(s,sink, Integer.MAX_VALUE);
			 flow += path;
		}
		return flow;
	}

	int dfs(int source, int sink, int val) {
		visited[source]=true;
		
		if(source==sink) {
			return val;
		}

		for(int i=0; i<graph.length; i++) 
			
			if( !visited[i] && graph[source][i] > 0 ) {
				
				int path = dfs(i, sink, Math.min(graph[source][i], val));
				
				if(path > 0) {
					graph[source][i] -=path;
					
					graph[i][source] +=path;
					
					return path;
				}
			}
		return 0;
	}

	public double getDistance(int i, int j) {
		return Math.sqrt(Math.pow(pieceInf[j][0]-pieceInf[i][0], 2)+ Math.pow(pieceInf[j][1] - pieceInf[i][1], 2));
	}


	public static void main(String[] args) throws Exception {
		int testCases = readInt();
		for (int i = 0; i < testCases; i++) {
			Penguins penguins = new Penguins();
			penguins.getInput();
			
			if (penguins.results.size() == 0)
				System.out.print("-1");
			else {
				for(Integer result : penguins.results) {
					System.out.print(result + " ");
				}
			}
			System.out.println();
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