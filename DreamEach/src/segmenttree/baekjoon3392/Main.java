package segmenttree.baekjoon3392;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 3392번 화성 지도 
 * https://www.acmicpc.net/problem/3392
 * 몰라서 블로그 답을 넣어 이해하려 했다
 * 아직도 세그먼트 트리가 왜 저모양으로 생겼는지 이해가 안간다 
 * 스위핑 + 세그먼트 트리 + lazy propagation 개념 비스무리 
 * 좌표를 받으면 x 좌표로 나열한다 
 * 구간합에 0~300_0000 까지의 y 높이를 가지고 있는다 
 * 직사각형의 왼쪽 선분이 들어오면 y에 해당하는 구간을 +1 시켜준다 (10~20)
 * 현재 x 값과 이전의 x 값 그리고 업데이트 된 y값을 곱하여 더해준다 
 * ㅠㅠ 
 * 세그먼트 트리의 cnt 배열은 lazy propagation 과 비슷한 역할인것 같다 
 * https://polohee81.tistory.com/13
 * https://devbelly.tistory.com/95
 * https://ioqoo.tistory.com/58
 * 
 * @author GitDeveloperKim
 *
 */


class Point implements Comparable<Point>{
	int x;
	int y1;
	int y2;
	boolean isLeft;
	Point (int x,int y1,int y2, boolean isLeft) {
		this.x = x;
		this.y1 = y1;
		this.y2 = y2;
		this.isLeft = isLeft;
	}
	
	@Override
	public int compareTo(Point o) {
		return this.x - o.x;
	}
}

public class Main {
	public static final int MAXN = 300_0001;	// 300_0001
	public static int N; 						// 1<= N <= 10000
	public static List <Point> arr;
	public static int [] tree;					// node가 나타내는 구간에 존재하는 0보다 큰 값들의 갯수 
	public static int [] cnt;					// cnt[n] 가 0보다 클경우 n이 나타내는 구간 전부는 0보다 크다는 의미 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		tree = new int [4*MAXN];	// 1 << (log2(300_0001)+1)
		cnt = new int [4*MAXN];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			arr.add(new Point(x1, y1, y2, true));
			arr.add(new Point(x2, y1, y2, false));
		}
		arr.sort((p1, p2) -> p1.x - p2.x);	// 오름차순 맞나?
		
		int prev = 0;
		int answer = 0;
		
		// 좌표가 두개씩으로 늘어나서 2N 
		for (int i = 0 ; i < 2*N; i++) {
			answer += (arr.get(i).x - prev) * tree[1];
			prev = arr.get(i).x;
			int val = arr.get(i).isLeft ? 1 : -1;			
			update (1, 0, MAXN, arr.get(i).y1, arr.get(i).y2-1, val);
		}
		System.out.println(answer);
	}
	
	public static void update (int n, int s, int e, int l, int r, int value) {
		if (r < s || e < l)
			return ;
		if (l <= s && e <= r) {
			// 구간에 전부 1을 더했다는 말과 같다 
			cnt[n] += value;
		} else {
			// 범위에 들어올지언정 하단 리프노드까지 내려간다 
			int mid = (s+e)/2;
			update (2*n, s, mid, l, r, value);
			update (2*n+1, mid+1, e, l, r, value);
		}
		
		if (cnt[n] == 0) {
			if (s != e) 
				// 리프 노드가 아니면 왼쪽 + 오른쪽 트리 값의 합 
				tree[n] = tree[2*n] + tree[2*n+1];
			else 
				// 리프 노드라면 0 
				tree[n] = 0;
		} else {
			// count 가 0보다 크다면 n에 업데이트가 되었다는 의미이며 구간의 크기만큼 업데이트 
			tree[n] = e-s+1;
		}
		
	}
}
