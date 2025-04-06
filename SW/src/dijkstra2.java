import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class d2 implements Comparable<d2> {
	int to, dist;

	d2(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(d2 o) {
		if(this.dist > o.dist) return 1;
		if(this.dist < o.dist) return -1;
		return 0;
	}
}

public class dijkstra2 {

	static int N,T;
	static boolean[] check;
	static int[] dist;
	private static final int INF = Integer.MAX_VALUE; 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		dist = new int[N];
		check = new boolean[N];
		
		for(int i=0; i<dist.length; i++) {
			dist[i] = INF;
		}			
		
		ArrayList<ArrayList<d2>> alist = new ArrayList<ArrayList<d2>>();
		
		for(int i=0; i<T; i++) {
			alist.add(new ArrayList<d2>());
		}
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			alist.get(from).add(new d2(to, dist));
		}
		
		dist[0] = 0;
		
		PriorityQueue<d2> pq = new PriorityQueue<>();
		pq.offer(new d2(0,0));
		
		while(!pq.isEmpty()) {
			d2 now_node = pq.poll();
			
			int now = now_node.to;
			
			if(check[now]) continue;
			check[now] = true;
			
			int size = alist.get(now).size();
			if(size > 0) {
				for(int i=0; i<alist.get(now).size(); i++) {
					int to = alist.get(now).get(i).to;
					int distance = alist.get(now).get(i).dist;
					if(dist[to] > dist[now] + distance) {
						dist[to] = dist[now] + distance;
						pq.add(new d2(to,dist[to]));
					}
				}
			}
		}
		
		if(dist[N-1] == INF) {
			bw.write("impossible");
		} else {
			bw.write(dist[N-1]+"");
		}
		
		br.close();
		bw.close();

	}

}
