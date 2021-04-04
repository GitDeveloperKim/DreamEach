package segmenttree.baekjoon3392;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * ���� 3392�� ȭ�� ���� 
 * https://www.acmicpc.net/problem/3392
 * ���� ��α� ���� �־� �����Ϸ� �ߴ�
 * ������ ���׸�Ʈ Ʈ���� �� ��������� ������� ���ذ� �Ȱ��� 
 * ������ + ���׸�Ʈ Ʈ�� + lazy propagation ���� �񽺹��� 
 * ��ǥ�� ������ x ��ǥ�� �����Ѵ� 
 * �����տ� 0~300_0000 ������ y ���̸� ������ �ִ´� 
 * ���簢���� ���� ������ ������ y�� �ش��ϴ� ������ +1 �����ش� (10~20)
 * ���� x ���� ������ x �� �׸��� ������Ʈ �� y���� ���Ͽ� �����ش� 
 * �Ф� 
 * ���׸�Ʈ Ʈ���� cnt �迭�� lazy propagation �� ����� �����ΰ� ���� 
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
	public static int [] tree;					// node�� ��Ÿ���� ������ �����ϴ� 0���� ū ������ ���� 
	public static int [] cnt;					// cnt[n] �� 0���� Ŭ��� n�� ��Ÿ���� ���� ���δ� 0���� ũ�ٴ� �ǹ� 
	
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
		arr.sort((p1, p2) -> p1.x - p2.x);	// �������� �³�?
		
		int prev = 0;
		int answer = 0;
		
		// ��ǥ�� �ΰ������� �þ�� 2N 
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
			// ������ ���� 1�� ���ߴٴ� ���� ���� 
			cnt[n] += value;
		} else {
			// ������ ���������� �ϴ� ���������� �������� 
			int mid = (s+e)/2;
			update (2*n, s, mid, l, r, value);
			update (2*n+1, mid+1, e, l, r, value);
		}
		
		if (cnt[n] == 0) {
			if (s != e) 
				// ���� ��尡 �ƴϸ� ���� + ������ Ʈ�� ���� �� 
				tree[n] = tree[2*n] + tree[2*n+1];
			else 
				// ���� ����� 0 
				tree[n] = 0;
		} else {
			// count �� 0���� ũ�ٸ� n�� ������Ʈ�� �Ǿ��ٴ� �ǹ��̸� ������ ũ�⸸ŭ ������Ʈ 
			tree[n] = e-s+1;
		}
		
	}
}
