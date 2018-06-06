package baekjoon14716;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int [][] mat;
	static boolean [][] visited;
	
	static int M ;
	static int N;
	
	static final int [] x = {-1, 0 , 1, -1, 1, -1, 0, 1};
	static final int [] y = {-1, -1, -1, 0, 0, 1, 1, 1};

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		M = in.nextInt();
		N = in.nextInt();
		
		int answer = 0;
		mat = new int [M][N];
		visited = new boolean [M][N];	// false default
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				mat [i][j] = in.nextInt();
			}			
		}
				
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && mat[i][j] == 1) {
					BFS(i,j);
					answer++;
				}
			}
		}	
		System.out.println(answer);
	}
	
	static void BFS (int r, int c) { 
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[]{r,c});	// init 
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			int [] a = q.poll();
			
			// 좌우 대각선
			for (int i = 0; i < 8; i++) {
				if ( (a[0]+x[i]) <0 || (a[1]+y[i]) <0 || (a[0]+x[i]) >= M  || (a[1]+y[i]) >= N) {
					continue;
				}
				if (mat[a[0]+x[i]][a[1]+y[i]]==1 && !visited[a[0]+x[i]][a[1]+y[i]] ) {
					q.add(new int[]{a[0]+x[i], a[1]+y[i]});
					visited[a[0]+x[i]][a[1]+y[i]] = true;
				}
			}
			
		}
	}

}