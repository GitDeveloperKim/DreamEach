package baekjoon1305;
import java.util.Scanner;

public class Main {
	static int L = 0;
	static String contents;
	
	public static void main(String[] args) {		
		Scanner in = new Scanner (System.in);
		L = in.nextInt();
		contents = in.next();		
		int [] pi = kmp(contents);		
		System.out.println(L - pi[L-1] );
	}
	
	static public int[] kmp (String p) {
		int [] pi = new int[L];
		int j = 0;
		pi[0] = 0;
		for (int i=1; i < L; i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if (p.charAt(i) == p.charAt(j)){
				pi[i] = ++j;
			}
		}		
		return pi; 
	}
}