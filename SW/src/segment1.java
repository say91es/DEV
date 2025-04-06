import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class segment1 {

	static int N, NN;
	static int[] tree, input;
	
	public static void main(String[] args) throws IOException {

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		N = Integer.parseInt(br.readLine());
		
		input = new int[7];
		input[1] = 1;
		input[2] = 3;
		input[3] = 5;
		input[4] = 7;
		input[5] = 9;
		input[6] = 11;
		
		N  = input.length-1;
		NN = 1;
		
		while(N > NN) {
			NN*=2;
		}
		
		tree = new int[NN*2];
		
		init(1,1,N);
		
		for(int i=1; i<tree.length; i++) {
			System.out.print(tree[i] + " ");
		}
		
	}

	private static void init(int node, int l, int r) {
		
		if(l==r) {
			tree[node] = input[l];
			return;
		}
		
		int mid = (l+r)/2;
		init(2*node, l, mid);
		init(2*node+1, mid+1, r);
		
	}

}
