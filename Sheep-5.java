import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Sheep {

    int width;
    int height;
    ArrayList<Integer> results = new ArrayList<Integer>();
    int flocks = 0;
    Node[][] graph;



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



    private class Node {
        boolean isSheep;
        boolean discovered;
        int x;
        int y;

        public Node(boolean sheep, int y, int x) {
            this.isSheep = sheep;
            this.discovered = false;
            this.x = x;
            this.y = y;
        }
        public void discoverNode() {
            this.discovered = true;
        }
        public boolean undiscovered() {
            return this.discovered != true;
        }

        @Override
        public String toString() {
            return "Node [isSheep=" + isSheep + ", discovered=" + discovered
                    + ", x=" + x + ", y=" + y + "]";
        }
        @SuppressWarnings("unused")
        public boolean isSheep() {
            return isSheep;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }

    /**
     * Loops through the graph then runs DFS. This should do the trick without me having to keep track of edges.
     */
    public void runThroughGraph() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if ((this.graph[i][j].undiscovered()) && (this.graph[i][j].isSheep)) {
                    this.flocks++;
                    dFS(graph[i][j]);
                }
            }
        }
    }

    public void dFS(Node startNode) {
    	//System.out.println();
    	//System.out.println(startNode.toString());

        startNode.discoverNode();
        int x = startNode.getX();
        int y = startNode.getY();
        // Goes left.
        if ((startNode.getX() > 0) && (this.graph[y][x-1].isSheep) && (this.graph[y][x-1].undiscovered())) {
        //	System.out.println("goes left");
            dFS(this.graph[y][x-1]);
        }
        // Goes right.
        if ((startNode.getX() < this.width-1) && (this.graph[y][x+1].isSheep) && (this.graph[y][x+1].undiscovered())) {
            dFS(this.graph[y][x+1]);
        }
        //Goes down.
        if ((startNode.getY() < this.height-1) && (this.graph[y+1][x].isSheep) && (this.graph[y+1][x].undiscovered())) {
            dFS(this.graph[y+1][x]);
        }
        if ((startNode.getY() > 0) && (this.graph[y-1][x].isSheep) && (this.graph[y-1][x].undiscovered())) {
            dFS(this.graph[y-1][x]);
        }


    }

    public void resetFlocks() {
        this.flocks = 0;
    }

    public void inputDimensions() throws Exception {
        this.height = readInt();
        this.width = readInt();
        this.graph = new Node[this.height][this.width];
    }
    public void fillGraph() throws Exception {

        String representation;
        char[] rep;
        for (int i = 0; i < this.graph.length; i++) {
            representation =  readString();
            rep = representation.toCharArray();
            for (int j = 0; j < this.graph[i].length; j++) {

                if (rep[j] == '#') {
                    this.graph[i][j] = new Node(true,i,j);
                } else {
                    this.graph[i][j] = new Node(false,i,j);

                }
            }
        }
    }


    public void showNodes() {
    	for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.println(this.graph[i][j].toString());
			}
		}
    }
    public static void  main(String[] args) throws Exception {
        Sheep sheep = new Sheep();
        int testCases = readInt();
        for (int i = 0; i < testCases; i++) {
            sheep.inputDimensions();
            sheep.fillGraph();
            sheep.runThroughGraph();
            sheep.results.add(sheep.flocks);
            sheep.resetFlocks();
        }
        for(Integer result: sheep.results) {
            System.out.println(result);
        }
    }
}