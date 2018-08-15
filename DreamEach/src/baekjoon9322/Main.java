package baekjoon9322;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static int T;
	static int n;
	static String [] P;
		
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		T = in.nextInt();
		for (int t = 0; t < T; t++) {
			n = in.nextInt();
			HashMap<String, Integer> pub1 = new HashMap<>();
			int [] tableMap = new int[n];
			P = new String[n];
			
			for (int i = 0; i < n; i++) {
				pub1.put(in.next(), i);
			}
			for (int i = 0; i < n; i++) {
				tableMap[i] = pub1.get(in.next());
			}
			
			for (int i = 0; i < n; i++) {
				P[tableMap[i]] = in.next();
			}
			
			for (int i = 0; i < n; i++) {
				System.out.print(P[i]+ " ");
			}
		}
	}
}