import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node1 implements Comparable<Node1> {
	
	int to,dist;
	
	Node1(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node1 r) {
		if(dist>r.dist) return 1;
		if(dist<r.dist) return -1;
		return 0;
	}
	
}

public class dijkstra1 {

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
		
		check = new boolean[N];
		dist = new int[N];
		
		ArrayList<ArrayList<Node1>> alist = new ArrayList<ArrayList<Node1>>(); 
		
		for(int i=0; i<N; i++) {
			alist.add(new ArrayList<Node1>());
		}
		
		for(int i=0; i<N; i++) {
			dist[i] = INF;
		}
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			alist.get(from).add(new Node1(to, dist));
		}
		
		PriorityQueue<Node1> pq = new PriorityQueue<Node1>();
		pq.offer(new Node1(0, 0));
		dist[0] = 0;
		
		while(!pq.isEmpty()) {
			Node1 now_node = pq.poll();
			
			int now = now_node.to;
			
			if(check[now]) continue;
			check[now] = true;
			
			int size = alist.get(now).size();
			if(size > 0 ) {
				for(int i=0; i<size; i++) {
					int to = alist.get(now).get(i).to;
					int distance = alist.get(now).get(i).dist;
					if(dist[to] > dist[now] + distance) {
						dist[to] = dist[now] + distance;
						pq.offer(new Node1(to, dist[to]));
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
