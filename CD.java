import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CD {

    // BufferedReader is quicker than Scanner.
    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");


    public static void main(String[] args) throws Exception {
        int N = readInt(); // Number of Cd's owned by jack.
        int M = readInt(); // Number of Cd's owned by Jill.

        while (N + M != 0) {

            List<Integer> cdList = new ArrayList<Integer>();

            for (int i = 0; i < N + M; i++) {
                cdList.add(readInt());
            }

            int total = (int) cdList.stream().count();

            // Cheats.
            int dis = (int) cdList.stream().distinct().count();

            System.out.println(total - dis);

            N = readInt();
            M = readInt();
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
