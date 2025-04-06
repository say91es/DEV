import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int to, dist;
	
	Node(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		if(dist-o.dist>0) return 1;
		if(dist-o.dist<0) return -1;
		return 0;
	}
}

public class b1753 {
	
	static int V,E,K;
	static boolean[] check;
	static int[] D;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		check = new boolean[V+1];
		D     = new int[V+1];
		
		ArrayList<ArrayList<Node>> alist = new ArrayList<ArrayList<Node>>();
		
		for(int i=0; i<=V; i++) {
			alist.add(new ArrayList<Node>());
			D[i] = INF;
		}
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			alist.get(from).add(new Node(to, dist));
		}
		
//		for(int i=1; i<E; i++) {
//			int size = alist.get(i).size();
//			for(int j=0; j<size; j++) {
//				bw.write("from : " + i + " to : " + alist.get(i).get(j).to + " dist : " + alist.get(i).get(j).dist + "\n");
//			}
//		}
		
//		for(int i=0; i<dist.length; i++) {
//			bw.write("i : " + i + " - dist : " + dist[i] + "\n");
//		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(K, 0));
		D[K] = 0;
		
		while(!pq.isEmpty()) {
			Node now_node = pq.poll();
			
			int now = now_node.to;
			
			if(check[now]) continue;
			check[now] = true;
			
			int size = alist.get(now).size();
			if(size > 0) {
				for(int i=0; i<size; i++) {
					int to   = alist.get(now).get(i).to;
					int dist = alist.get(now).get(i).dist;
					if(D[to] > D[now] + dist) {
						D[to] = D[now] + dist;
						pq.offer(new Node(to, D[to]));
					}
				}
			}			
		}
		
		for(int i=1; i<=V; i++) {
			if(D[i] == INF) {
				bw.write("INF\n");
			} else {
				bw.write(D[i] + "\n");	
			}			
		}
				
		br.close();
		bw.close();
		
	}

}
