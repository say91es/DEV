import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class segment2 {
	
	static int N,M,NN;
	static int[] tree, lazy;

	public static void main(String[] args) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		NN = 1;
		
		while(N > NN) {
			NN *= 2;
		}
		
		tree = new int[NN*2];
		lazy = new int[NN*2];
		
//		System.out.println("NN : " + NN);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==0) {
				updateRange(1, 1, NN, b, c);

			} else {
				bw.write(query(1, 1, NN, b, c) + "\n");
			}			
		}
		
//		update(1, 1, N, 1, 2, 4);
//		bw.write(query(1,1,N,1,2) + "\n");
		
//		for(int i=1; i<tree.length; i++) {
//			bw.write(tree[i]+" ");
//		}
				
		br.close();
		bw.close();

	}

//	private static void init(int node, int l, int r) {
//		if(l==r) {
//			tree[node] = input[l];
//			return;
//		}
//		
//		int mid = (l+r)/2;
//		init(2*node, l, mid);
//		init(2*node+1, mid+1, r);
//		
//		tree[node] = tree[2*node] + tree[2*node+1];
//	}

	private static int query(int node, int l, int r, int ql, int qr) {
		updatelazy(node, l, r);
		
		if(ql <= l && r <= qr) {
			return tree[node];
		}
		if(ql > r || qr < l) return 0;
		
		int mid = (l+r)/2;
		int left  = query(2*node, l, mid, ql, qr);
		int right = query(2*node+1, mid+1, r, ql, qr);
		
		return left+right;
		
	}

	private static void updatelazy(int node, int l, int r) {
		if(lazy[node] == 0) return;
		
		tree[node] = (r-l+1) - tree[node];
		
		if(l!=r) {
			lazy[node*2] ^= 1;
			lazy[node*2 + 1] ^=1;
		}
		lazy[node] = 0;
	
}

	private static int updateRange(int node, int l, int r, int ql, int qr) {
		updatelazy(node, l, r);
		
		if(ql <= l && r <= qr) {
			lazy[node] ^= 1;
			updatelazy(node, l, r);
			return tree[node];
		}
		if(ql > r || qr < l) return tree[node];
		
		int mid = (l+r)/2;
		updateRange(2*node, l, mid, ql, qr);
		updateRange(2*node+1, mid+1, r, ql, qr);
		
		return tree[node] = tree[2*node] + tree[2*node+1];
	}

}
