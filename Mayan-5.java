import java.io.*;
import java.util.*;

public class Mayan {
	
	
	private final int maxLimitAscii= 123;
	int glyphLength;
	int sequenceLength;
	String glyph;
	String sequence;
	int results;
	int glyphWindow[]; 
	int sequenceWindow[];
	
	public Mayan() throws Exception {
		this.glyphLength = readInt();
		this.sequenceLength = readInt();
		
		this.glyph = readString();
		this.sequence = readString();
		
		this.glyphWindow = new int[this.maxLimitAscii];
		this.sequenceWindow = new int[this.maxLimitAscii];
		this.results = 0;
		
		getGlyphWindow();
		getSequenceWindow();
	}
	
	
	static BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in));
	static StringTokenizer st = new StringTokenizer("");
	
	
	public void getGlyphWindow() {
		for (int i = 0; i < glyphLength; i++) {
			this.glyphWindow[this.glyph.charAt(i)]++;	
		}
	}
	
	public void getSequenceWindow() {
		for (int i = 0; i < glyphLength; i++) {
			this.sequenceWindow[this.sequence.charAt(i)]++;
		}
	}

	public void incrementResults() {
		this.results++;
	}
	
	public void theMainLoopThatWillRunSlidingWindowsAndSolveEverything() {
		
		for (int i = 0; i < this.sequenceLength-this.glyphLength; i++) {
			if (Arrays.equals(this.glyphWindow, this.sequenceWindow)) {
				this.incrementResults();
			}
			
			this.sequenceWindow[this.sequence.charAt(i)]--;
			this.sequenceWindow[this.sequence.charAt(i+glyph.length())]++;
		}
		if (Arrays.equals(this.glyphWindow, this.sequenceWindow)) {
			this.incrementResults();
		}

		}
	
	
	public static void main(String[] args) throws Exception {
		Mayan mayan = new Mayan();
		mayan.theMainLoopThatWillRunSlidingWindowsAndSolveEverything();
		System.out.println(mayan.results);
		
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
