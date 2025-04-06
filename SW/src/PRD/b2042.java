package PRD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b2042 {

	static int N,M,K,NN;
	static long[] tree;
	static int[] input;
	
	public static void main(String[] args) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[N+1];
		NN = 1;
		
		while(N > NN) {
			NN *= 2;
		}
		
		tree = new long[NN*2];
		
		for(int i=1; i<=N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, 1, N);
		
		for(int i=0; i<M+K; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a==1) {
				long c = Long.parseLong(st.nextToken());
				update(1, 1, N, b, b, c);
			} else {
				int c = Integer.parseInt(st.nextToken());
				bw.write(query(1, 1, N, b, c)+"\n");
			}
		}
		
//		for(int i=1; i<tree.length; i++) {
//			bw.write(tree[i] + " ");
//		}
		
		br.close();
		bw.close();

	}

	private static long query(int node, int l, int r, int ql, int qr) {
		if(ql <= l && qr >= r) {
			return tree[node];
		}
		if(ql > r || qr < l) {
			return 0;
		}
		int mid=(l+r)/2;
		long left = query(2*node, l, mid, ql, qr);
		long right = query(2*node+1, mid+1, r, ql, qr);
		
		return left+right;
		
	}

	private static void update(int node, int l, int r, int ql, int qr, long value) {
		if(ql <= l && qr >= r) {
			tree[node] = value;
			return;
		}
		if(ql > r || qr < l) {
			return;
		}
		int mid=(l+r)/2;
		update(2*node, l, mid, ql, qr, value);
		update(2*node+1, mid+1, r, ql, qr, value);
		
		tree[node] = tree[2*node] + tree[2*node+1];
		
	}

	private static void init(int node, int l, int r) {
		if(l==r) {
			tree[node] = input[l];
			return;
		}
		
		int mid=(l+r)/2;
		init(2*node, l, mid);
		init(2*node+1, mid+1, r);
		
		tree[node] = tree[2*node] + tree[2*node+1];
		
	}

}
