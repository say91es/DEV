package PRD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b9345 {
	
	static int T, N, K, NN;
	static int[] tree, input;
	static int temp1, temp2;

	public static void main(String[] args) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=0; test_case<T; test_case++) {
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			temp1 = temp2 = 0;
			
			input = new int[N+1];
			NN=1;
			while(N > NN) {
				NN *= 2;
			}
			
			tree = new int[2*NN];
			
			for(int i=1; i<=N; i++) {
				input[i] = i;
			}
			
			init(1, 1, N);
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				temp1 = tree[b];
				temp2 = tree[c];
				
				if(a==0) {
//					update(1, 1, N, b, c);
				} else {
//					query(1, 1, N, b, c);
				}
			}
			
			temp1 = tree[1];
			temp2 = tree[2];
			
			update(1, 1, N, 1, 2);
//			query(1, 1, N, 1, 2);
			
			for(int i=1; i<tree.length; i++) {
				bw.write(tree[i] + " ");
			}
			
			br.close();
			bw.close();
			
		}

	}

	private static int query(int node, int l, int r, int ql, int qr) {
		if(ql <= l && qr >= r) {
			return tree[node];
		}
		if(ql > r || qr < l) return 0;
		
		int mid=(l+r)/2;
		int left = query(2*node, l, mid, ql, qr);
		int right = query(2*node+1, mid+1, r, ql, qr);
		
		return left + right;
		
	}

	private static void update(int node, int l, int r, int ql, int qr) {

		if(ql <= l && qr >= r) {
			tree[node] = temp2;
			tree[node] = temp1;
			return;
		}
		if(ql > r || qr < l) return;
		
		int mid=(l+r)/2;
		update(2*node, l, mid, ql, qr);
		update(2*node+1, mid+1, r, ql, qr);
		
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
