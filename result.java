import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

class Result
{
public static List<String> classifyEdges(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight)
{
String[] result = new String[gFrom.size()];
//fill all values to No for result array
Arrays.fill(result, "No");
if((gNodes>=2 && gNodes<=3000)&&(gFrom.size()>=1&&gFrom.size()<=(Math.max(Math.pow(10,5),(gNodes*gNodes-1)/2))))
{
// PriorityQueue used as a Min heap
PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
// Map is used to store road number
Map<Integer, List<Integer>> mp = new HashMap<>();
// Stores minimum distance of every node from Source
int[][] dst = new int[gNodes][gNodes];
boolean[][] path = new boolean[gNodes][gNodes];
int[] shortest = new int[gNodes];
//map the edges with index
Map<String, Integer> mapEdge= new HashMap<>();
int count = 0;
// Build Adjacency matrix of the graph
for(int i=0; i<gFrom.size(); i++) {
// store value from gFrom to source s
int s = gFrom.get(i)-1;
// store value from gTO to destination d
int d = gTo.get(i)-1;
mp.putIfAbsent(s, new ArrayList<>());
mp.get(s).add(d);
mp.putIfAbsent(d, new ArrayList<>());
mp.get(d).add(s);
//store weight in dst matrix for bidirectional
dst[s][d] = gWeight.get(i);
dst[d][s] = gWeight.get(i);
mapEdge.put(s+"-"+d, count++);

}
queue.offer(new int[]{0, 0, 0});
//to store visited nodes
Set<Integer> visited = new HashSet<>();
while (!queue.isEmpty()) {
//get edge with weight from queue
int[] edge = queue.poll();
int dist = edge[1];
int n = edge[0];
int prev = edge[2];
//check whether n is already visited or not
if(visited.contains(n)) {
if(shortest[n] == dist)
path[n][prev] = true;
continue;
}
shortest[n] = dist;
visited.add(n);
path[n][prev] = true;
for(int neighbor : mp.getOrDefault(n, new ArrayList<>())) {
queue.offer(new int[]{neighbor, dist + dst[n][neighbor], n});
}
}
ArrayDeque<Integer> q = new ArrayDeque<>();
q.offer(gNodes-1);
visited = new HashSet<>();
while(!q.isEmpty()) {
//get the vertex from the queue
int v = q.poll();
//check whether v is already visited or not
if(visited.contains(v))
continue;
//if v not visited then add it the visited set
visited.add(v);
for(int j=0; j<gNodes; j++){
if(path[v][j]) {
q.offer(j);

if(mapEdge.containsKey(v+ "-" + j))
result[mapEdge.get(v+ "-" + j)] = "YES";
if(mapEdge.containsKey(j + "-" + v))
result[mapEdge.get(j + "-" + v)] = "YES";
}
}
}
}
//convert string array to
return new ArrayList<String>(Arrays.asList( result));
}

}
public class Solution
{
public static void main(String[] args) throws IOException {
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

int gNodes = Integer.parseInt(gNodesEdges[0]);
int gEdges = Integer.parseInt(gNodesEdges[1]);

List<Integer> gFrom = new ArrayList<>();
List<Integer> gTo = new ArrayList<>();
List<Integer> gWeight = new ArrayList<>();

IntStream.range(0, gEdges).forEach(i -> {
try {
String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

gFrom.add(Integer.parseInt(gFromToWeight[0]));
gTo.add(Integer.parseInt(gFromToWeight[1]));
gWeight.add(Integer.parseInt(gFromToWeight[2]));
} catch (IOException ex) {
throw new RuntimeException(ex);
}
});

List<String> result = Result.classifyEdges(gNodes, gFrom, gTo, gWeight);

bufferedWriter.write(result.stream().collect(joining("\n")) + "\n");

bufferedReader.close();
bufferedWriter.close();
}

}

Ti