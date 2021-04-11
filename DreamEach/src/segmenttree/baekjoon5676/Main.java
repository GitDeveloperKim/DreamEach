package segmenttree.baekjoon5676;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 세그먼트 트리를 이용해 구간 업데이트와 구간 곱쿼리를 구해야한다 
2. 가질 수 있는 최대값 100 에 10만번 곱 연산을 할 수 있으므로 값의 overflow 가 발생한다 (부호만 알면 됨) 
*/
public class Main {
	public static int N;         // 1<= N, K <= 100_000
	public static int K;         // -100 <= x <= 100
	public static int [] x;      // input 
	public static int [] tree;   // segment tree
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb;
		StringTokenizer st;
		
		String str = ""; // 초기화 안해주면 null pointer 오류 발생 
		while ((str=br.readLine())!= null && str.length() !=0 ) {
			sb = new StringBuffer();
			st = new StringTokenizer(str); // while에서 검사한 값 입력
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			x = new int [N+1];
			tree = new int [4*N];
			
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				int temp = Integer.parseInt(st.nextToken());
				x[n] = value(temp); // 오버 플로우 방지 
			}
			
			init_tree(1, 1, N); // 세그먼트 트리 초기화 
			
			for (int k = 1; k <= K; k++) {
				String [] temp = br.readLine().split(" ");
				if (temp[0].equals("C")) {
					// 변경 명령 
					int index = Integer.parseInt(temp[1]);
					int val = Integer.parseInt(temp[2]);
					update (index, value(val),1, 1, N);
				} else if (temp[0].equals("P")) {
					// 곱셈 명령
					int i = Integer.parseInt(temp[1]);
					int j = Integer.parseInt(temp[2]);
					int answer = query (1, 1, N, i, j);
					
                    // 출력 
					if (answer == 0) {
						sb.append(answer+"");
					} else if (answer > 0) {
						sb.append("+");
					} else if (answer < 0) {
						sb.append("-");
					}
				}
			}
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}		
		bw.close();
		bw.close();
	}
	
	public static int init_tree(int index, int s, int e) {
		if (s == e) {
			// leaf node
			return tree[index] = x[s];
		} else {
			int mid = (s+e)/2;
			return tree[index] = init_tree(2*index, s, mid) * init_tree(2*index+1, mid+1, e);
		}
	}
	
	public static int update (int find, int val, int index, int s, int e) {
		if (find < s || e < find) {
			return tree[index];
		} 
		else if (s == e) {
			return tree[index] = val;
		}
		int mid = (s+e)/2;
		return tree[index] = update (find, val, 2*index ,s, mid) * update (find, val, 2*index+1 ,mid+1, e);
		
	}
	
	public static int query (int index, int s, int e, int l, int r) {
		if (r < s || e < l) {
			return 1;
		} 
		else if (l<= s && e <= r) {
			return tree[index];
		}
		int mid = (s+e)/2;
		return query (2*index, s, mid,l,r) * query(2*index+1, mid+1, e, l, r);
	}
	
	public static int value (int x) {
		int result = 0;
		if (x > 0) {
			result = 1;
		} else if (x == 0) {
			result = 0;
		} else {
			result = -1;
		}
		return result;
	}
}
