package baekjoon1013;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		Pattern p = Pattern.compile("(100+1+|01)+");
				
		int T=in.nextInt();
		for (int t = 0; t < T; t++){
			String str = in.next();			
			Matcher m = p.matcher(str);
			if (m.matches()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}		
	}
}