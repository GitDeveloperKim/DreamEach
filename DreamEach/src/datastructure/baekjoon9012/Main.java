package datastructure.baekjoon9012;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static int T;
	public static boolean p;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		T = Integer.parseInt(in.nextLine());
		
		while (T >0) {
			String str = in.nextLine();
			Stack <Integer>s = new Stack<>();
			Stack <Integer>t = new Stack<>();
			p = true;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(') {
					s.push(1);
				}
				if (str.charAt(i) == ')') {
                    // '(' 이 없는 상태에서 ')'가 들어가면 감싸는 모양이 안되기 때문에 틀린 케이스
					if (s.isEmpty()){
						p = false;
						break;
					}
					s.pop();
				}
			}
			if (s.isEmpty() && p!=false) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}			
			T--;
		}
	}

}
