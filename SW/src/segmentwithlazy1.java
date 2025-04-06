import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class segmentwithlazy1 {
	
	static int N,M,NN,cnt;
	static int[] tree, lazy;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		NN = 2 << (int) (Math.ceil(Math.log(N)) / Math.log(2));
		cnt=0;
		
//		while(N > NN) {
//			NN *= 2;
//		}
		
		tree = new int[NN << 1];
		lazy = new int[NN << 1];
		
		System.out.println("NN : " + NN);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==0) {
				updateRange(1, 1, NN, b, c, 1);
			} else {
				bw.write(query(1, 1, NN, b, c)+"\n");
			}
		}
		
//		updateRange(1, 1, NN, 1, 3);
		
//		for(int i=1; i<tree.length; i++) {
//			bw.write(tree[i] + " ");
//		}
		
		br.close();
		bw.close();

	}

	private static int query(int node, int l, int r, int ql, int qr) {
		updateWithLazy(node, l, r);
		
		if(ql <= l && qr >=r) {
			return tree[node];
		}
		if(ql > r || qr < l) {
			return 0;
		}
		
		int mid = (l+r)/2;
		int left = query(2*node, l, mid, ql, qr);
		int right = query(2*node+1, mid+1, r, ql, qr);
		
		return left + right;
		
	}

	private static int updateRange(int node, int l, int r, int ql, int qr, int value) {
		updateWithLazy(node, l, r);
		
		if(ql <= l && qr >=r) {
			lazy[node] ^= 1;
			updateWithLazy(node, l, r);
			return tree[node];
		}
		if(ql > r || qr < l) {
			return tree[node];
		}
		
		int mid = (l+r)/2;
		updateRange(2*node, l, mid, ql, qr, value);
		updateRange(2*node+1, mid+1, r, ql, qr, value);
		
		return tree[node] = tree[2*node] + tree[2*node+1];
		
	}

	private static void updateWithLazy(int node, int l, int r) {
		if(lazy[node] == 0) return;
		
		tree[node] = (r-l+1) - tree[node];
		
		if(l!=r) {
			lazy[node << 1] ^= 1;
			lazy[(node << 1) + 1] ^= 1; 
		}
		lazy[node] = 0;
		
	}

}
