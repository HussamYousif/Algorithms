import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class Gold {

    int width;
    int height;
    Node graf[][];
    Node player;

    private int score = 0;

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

    public void inputDimensions() throws Exception {
        this.width = readInt();
        this.height = readInt();
        this.graf = new Node[this.height][this.width];
    }

    public void fillGraph() throws Exception {
        char[] rep;
        for (int i = 0; i < this.graf.length; i++) {
            String representation = readString();
            rep = representation.toCharArray();
            for (int j = 0; j < this.graf[i].length; j++) {
                this.graf[i][j] = new Node(i,j,rep[j]);
                if (rep[j] == 'P') {
                    this.player = graf[i][j];
                }
            }
        }
    }
    public Node getNode(int y, int x) {
        return this.graf[y][x];
    }
    public class Node {
        boolean discovered;
        int x;
        int y;
        char rep;
        public Node(int y,int x, char rep) {
            this.y = y;
            this.x = x;
            this.rep = rep;
        }
        @Override
        public String toString() {
            return "Node [discovered=" + discovered + ", x=" + x + ", y=" + y
                    + ", rep=" + rep + "  isWalled = " + isWalled() + "  isGold = " + isGold() + "  isTrapped = " + isTrap() + "  isFloor = " + isFloor() + " ]";
        }
        public boolean isTrap() {
            return (this.rep == 'T');
        }
        public boolean isGold() {
            return (this.rep == 'G');
        }
        public boolean isWalled() {
            return (this.rep == '#');
        }
        public boolean isFloor() {
            return (this.rep == '.');
        }
        public boolean isDiscovered() {
            return (this.discovered);
        }
        public int getX() {
            return this.x;
        }
        public int getY() {
            return this.y;
        }
    }

    public void showNodes() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.println(this.graf[i][j].toString());
            }
        }
    }


    public void nonRecursiveDFS(Node startNode) {
        Stack<Node> nodeStack = new Stack<Node>();
        nodeStack.push(startNode);
        while(!nodeStack.empty()) {
            Node node = nodeStack.pop();

            if (node.isGold()) {
                this.score++;
                node.rep = '.';
            }

            if (node.isDiscovered() || node.isWalled() || node.isTrap() || this.neighbouringTrap(node.getY(), node.getX())) {
                continue;
            }
            node.discovered = true;

            // Next node goes left.
            if (startNode.getX() > 0) {
                nodeStack.push(getNode(node.getY(), node.getX()-1));

            }

            // Next node goes right.
            if (startNode.getX() < this.width-1) {
                nodeStack.push(getNode(node.getY(),node.getX()+1));
            }
            // Node goes up.
            if (startNode.getY() > 0) {
                nodeStack.push(getNode(node.getY()+1,node.getX()));
            }
            // Node goes down.
            if (startNode.getY() < this.height-1) {
                nodeStack.push(getNode(node.getY()-1,node.getX()));
            }
        }
    }
    public void dFS(Node startNode) {

        // Update score
        if (startNode.isGold()) {
            startNode.rep = '.';
            ++this.score;
        }
        // Termination statement
        if (startNode.isDiscovered() || startNode.isWalled() || startNode.isTrap() || this.neighbouringTrap(startNode.getY(), startNode.getX())) {
            startNode.discovered = true;
            return;
        }
        startNode.discovered = true;
        // Next node goes left.
        if (startNode.getX() > 0) {
            dFS(getNode(startNode.getY(), startNode.getX()-1));
        }

        // Next node goes right.
        if (startNode.getX() < this.width-1) {
            dFS(getNode(startNode.getY(), startNode.getX()+1));

        }
        // Node goes up.
        if (startNode.getY() > 0) {
            dFS(getNode(startNode.getY()-1, startNode.getX()));
        }
        //down
        if (startNode.getY() < this.height-1) {
            dFS(getNode(startNode.getY()+1, startNode.getX()));
        }
    }

    public boolean neighbouringTrap(int y, int x) {

        // Left
        if (getNode(y,x-1).isTrap()) {
            return true;
        }
        // Right
        if (getNode(y,x+1).isTrap()) {
            return true;
        }
        // Up
        if (getNode(y-1,x).isTrap()) {
            return true;
        }
        // down.
        else return (getNode(y+1,x).isTrap());

    }

    public Node getPlayer() {
        return this.player;
    }
    public static void main(String[] args) throws Exception {
        Gold gold = new Gold();
        gold.inputDimensions();
        gold.fillGraph();
        gold.dFS(gold.getPlayer());
        System.out.println(gold.score);
    }
}