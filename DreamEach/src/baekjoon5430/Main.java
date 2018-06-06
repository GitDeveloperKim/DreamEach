package baekjoon5430;

import java.util.Scanner;

public class Main {		
	static String [] answer;

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int T = in.nextInt();				 // T < 100
		answer = new String[T];
		for (int t = 0; t < T; t++) {			
			boolean flag = false;
			
			String p = in.next();		// operator	1<=p<= 10 0000
			int n = in.nextInt();			// 0<n< 10 0000
			String op = in.next();		// operand
			
			StringBuffer temp_answer;
			
			String temp = op.substring(1, op.length()-1);	// delete [ ]
			String [] temp1 = temp.split(",");					// split ,
						
			int front = 0;
			int rear = n-1;			
			int cnt = 0;				
			
			temp_answer = new StringBuffer();				// answer	
			
			for (int a = 0; a <p.length() ; a++) {
				if (p.charAt(a) == 'R') {
					cnt++;
				} else if (p.charAt(a) == 'D') {
					if (cnt%2 == 0) {
						front++;
					} else {
						rear--;
					}
					if (front > rear+1) {
						//System.out.print("error");
						temp_answer.append("error");
						flag = true;
						break;
					}
				}
			}				
				
			if (!flag) {				
				//System.out.print("[");
				temp_answer.append("[");
				if (n!=0) {					
					if ( (cnt%2) == 0 ) {
						for (int i = front; i <= rear; i++) {
							//System.out.print(temp1[i]);
							temp_answer.append(temp1[i]);
							if (i != rear) {
								//System.out.print(",");
								temp_answer.append(",");
							}
						}
					} else {
						for (int i = rear; i >= front; i--) {
							//System.out.print(temp1[i]);
							temp_answer.append(temp1[i]);
							if (i != front) {
								//System.out.print(",");
								temp_answer.append(",");
							}
						}
					}
				}
				//System.out.print("]");
				temp_answer.append("]");
			}	// flag end 
		
			answer[t] = temp_answer.toString();
			
		} // test case 	
		
		for (int i = 0; i < T; i++) {
			System.out.println(answer[i]);
			/*if((i+1) != T) {
				System.out.println();
			}*/
		}
	} // main end	
}