package tree.DP.baekjoon1949;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 백준 1949 우수마을 
// 참고 : https://blog.naver.com/s0355/222386156622
/**
 * dfs를 이용하여 트리를 순회하면서 
 * 점화식을 이용하여 최대의 마을 주민의 값을 찾는다 
 * 
 * 점화식  
 * 
 * 비교하여 더 높은 점수를 선택 
 * 1. 현재 우수 마을인 경우 -> 다음 마을은 우수마을이 될 수 없다 
 * 2. 현재 우수 마을이 아닌 경우 -> 다음 마을이 우수 마을이거나 다음 마을이 우수마을이 아닌 경우가 있다 
 * 
 * //일반마을 하위에 일반마을이 또 오는 경우도 가능하니, (예 : 우수-일반-일반-우수)
   //우수마을 주민수를 최대로 구하려다보면 max로 값을 가져오면서 (우수-일반-우수) 또는 (우수-일반-일반-우수) 형태로 될것?
   [출처] [문제풀이] Tree/DP - 우수 마을(백준 1949)|작성자 웁스


 * @author default
 *
 */

public class Main {
	public static int N; 
	public static int [] citizen;
	public static int dp[][];
	public static boolean [] isVisited;
	
	public static ArrayList<ArrayList<Integer>> g;	// 인접 행렬 
	
	public static void dfs (int cur) {
		if (isVisited[cur])
			return;
		isVisited[cur] = true;
		
		dp[cur][0] = 0;
		dp[cur][1] = citizen[cur];
		
		for (int next : g.get(cur)) { // 인접 행렬 순회 참고 
			if (isVisited[next])
				continue;
			dfs(next);
			dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
			dp[cur][1] += dp[next][0];
		}
	}
	

	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		citizen = new int[N+1];
		isVisited = new boolean[N+1];
		dp = new int[N+1][2];	// N: 마을 번호 // state 0: 우수마을 아님 1: 우수마을 
		g = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i <= N; i++) {
			g.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			citizen[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			g.get(from).add(to);
			g.get(to).add(from);
		}
		
		dfs(1);
		sb.append(Math.max(dp[1][0], dp[1][1])); // 최종 결과값이 1번 노드(루트)에 찾아진다 
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
