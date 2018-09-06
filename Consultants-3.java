import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Retry, this time away with all the unessecary fluff and use DFS instead of BFS. 
 * 
 * No objects or anything of the like. I'll keep the same idea of having two dummy nodes, one as a source and another as a sink. 
 * 
 * @author hussam
 *
 */
public class Consultants {
	
	public static int maxFlow(int s, int sink, int[][] graph, int graphLength) {
		int flow=0;
		int path = -1;
		
		boolean visited[];


		while (path != 0) {
			visited = new boolean[graphLength];
			 path = dfs(0,sink, Integer.MAX_VALUE, visited, graph);
			 flow += path;
		}
		return flow;
	}

	public static int dfs(int source, int sink, int val, boolean[] visited, int[][] graph) {
		visited[source]=true;
		
		if(source==sink) {
			return val;
		}

		for(int i=0; i<graph.length; i++) 
			
			if( !visited[i] && graph[source][i] > 0 ) {
				
				int path = dfs(i, sink, Math.min(graph[source][i], val), visited, graph);
				
				if(path > 0) {
					graph[source][i] -=path;
					
					graph[i][source] +=path;
					
					return path;
				}
			}
		return 0;
	}


	public static void main(String[] args) throws Exception {

		int testCases = readInt();

		for (int test = 0; test < testCases; test++) {
			int results = 0;
			int nrOfCompanies = readInt();
			int nrOfConsultants = readInt();
			int source = 0;
			int sink = nrOfCompanies + nrOfConsultants + 1;
			int graphLength  = nrOfCompanies + nrOfConsultants + 2;

			int[][] graph = new int[nrOfCompanies+nrOfConsultants+2][nrOfCompanies+nrOfConsultants+2];

			for (int i = 1; i < nrOfConsultants+1; i++) {
				int consultants = readInt();

				graph[0][i] = 1;
				
				
				
				for (int j = 0; j < consultants; j++) {
					int com = readInt();
					graph[i][nrOfConsultants+1+com] = 1;
				}
			}

			for (int i = nrOfConsultants+1; i < graph.length-1; i++) {
				graph[i][sink] = 1;
			}
			//showGraph(graph);

			
			
			//boolean impossible = isImpossible(graph, nrOfConsultants, nrOfCompanies);
			
			boolean[] companiesVisited = new boolean[nrOfCompanies];
			Arrays.fill(companiesVisited, false);
			for (int i = 1; i < nrOfConsultants+1; i++) {
				for (int j = 0; j < nrOfCompanies; j++) {
					if (graph[i][j+nrOfConsultants+1] == 1) {
						//System.out.printf("dat mess: %d \n",j+nrOfConsultants+1);
						//System.out.printf("company: %d, consultant: %d \n", j, i);
						companiesVisited[j] = true;
					}
				}
			}
			/*
			for (int i = 0; i < companiesVisited.length; i++) {
				if (companiesVisited[i])
					System.out.printf("visited %d \n", i);
				else {
					System.out.printf("didn't visit %d \n", i);
				}
			}*/
			
			boolean impossible = checkPosibility(companiesVisited);
			
			if (impossible) {
				System.out.println("impossible");
				continue;
			}

			for(int flow = -1; flow != 0; flow = maxFlow(source, sink, graph, graphLength)) {
				//System.out.println("flow:  " + flow);
				//howGraph(graph);
				
				for (int i = 1; i < nrOfConsultants+1; i++) {
					graph[0][i] = 1;
				}
				
				
				if (flow > 0)
					results++;
			}
			System.out.println(results);
			
			
		}
		
		
	}
	private static boolean checkPosibility(boolean[] companies) {
		for (int i = 0; i < companies.length; i++) {
			if (!companies[i])
				return true;
		}
		return false;
	}

/**
	private static boolean isImpossible(int[][] graph, int nrOfConsultants, int companies) {
		for (int i = nrOfConsultants+1; i < graph.length-1; i++) {
			for (int j = 1; j < nrOfConsultants+1; j++) {
				if (graph[j][i] == 1) {
					System.out.printf("%d, %d broken\n",i,j);
					break;
				}
				if (j == graph.length-2 && graph[j][i] == 0) {
					return true;
				}
			}
		}
		return false;
	}*/

	static void showGraph(int[][] graph) {
		System.out.println();
		for (int i = 0; i < graph.length; i++) {
			System.out.print("[ ");
			for (int j = 0; j < graph[i].length; j++) {
				System.out.printf("%d, ",graph[i][j]);
			}
			System.out.print(" ]");
		}
		System.out.println("]");
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
