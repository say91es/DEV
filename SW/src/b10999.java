import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b10999 {
	
	static int N,M,K,NN;
	static long[] tree, lazy;
	static int[] input;

	public static void main(String[] args) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		NN = 1;
		
		while(N>NN) {
			NN *= 2;
		}
		
		tree = new long[2*NN];
		lazy = new long[2*NN];
		input = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, 1, N);
		
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(a==1) {
				long b = Long.parseLong(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				long d = Long.parseLong(st.nextToken());
				updateRange(1, 1, N, b, c, d);
			} else {
				long b = Long.parseLong(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				bw.write(query(1, 1, N, b, c) + "\n");
			}
		}
		
		br.close();
		bw.close();
		
	}

	private static long query(int node, int l, int r, long ql, long qr) {
		updatewithlazy(node, l, r);
		
		if(ql <= l && qr >= r) {
			return tree[node];
		}
		if(ql > r || qr < l) {
			return 0;
		}
		
		int mid=(l+r)/2;
		long left = query(2*node, l, mid, ql, qr);
		long right = query(2*node+1, mid+1, r, ql, qr);
		
		return left + right;
		
	}

	private static void updatewithlazy(int node, int l, int r) {
		if(lazy[node]==0) return;
		
		tree[node] += (r-l+1)*lazy[node];
		
		if(l!=r) {
			lazy[2*node] += lazy[node];
			lazy[2*node+1] += lazy[node];
		}
		lazy[node] = 0;
		
	}

	private static void updateRange(int node, int l, int r, long ql, long qr, long value) {
		int length = r-l+1;
		updatewithlazy(node, l, r);
		
		if(ql > r || qr < l) {
			return;
		}
		
		if(ql <= l && qr >= r) {
			if(l!=r) {
				lazy[2*node] += value;
				lazy[2*node+1] += value;
			}			
			tree[node] += value * length;
			return;
		}
		
		int mid=(l+r)/2;
		updateRange(2*node, l, mid, ql, qr, value);
		updateRange(2*node+1, mid+1, r, ql, qr, value);
		
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
