import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class binarysearch_1 {

	static int N,K;
	static int[] input;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
		input = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<K; i++) {
//			bw.write("i : " + i + " ");			
			int temp = Integer.parseInt(st.nextToken());
//			bw.write("temp : " + temp + " ");
			if(bs(temp)) bw.write("O");
			else bw.write("X");
		}
		
		br.close();
		bw.close();
		
	}

	private static boolean bs(int temp) {
		int l = 0;
		int r = N-1;
		
		while(l <= r) {
			int mid = (l+r)/2;
			if(input[mid] == temp) {
				return true;
			} else if(input[mid] < temp) {
				l = mid+1;
			} else {
				r = mid-1;
			}				
		}
		
		return false;
	}

}
