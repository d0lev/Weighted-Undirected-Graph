<p>&nbsp;</p>
<p><img src="https://i.ibb.co/M2BwdDW/background.jpg"></p>
<h2>Weighted Undirected Graph</h2>
<p>Project as part of the object-oriented course JAVA language.</p>
<p>The above project deals with the construction of the data structure - an Undirected and unweighted graph, using other data structures.</p>
<p><strong>In this project you can find algorithms that deal with solving various problems:</strong></p>
<ol>
<li>Connectivity of graph.</li>
<li>The length of the shortest path between two nodes.</li>
<li>The list of nodes that are in the shortest path between two nodes.</li>

</ol>
<p>The classes of the project :</p>
<ol>
<li><strong>WGraph_DS</strong>  which implements the interface : <strong>weighted_graph</strong></li>

</ol>
<figure><table>
<thead>
<tr><th>Data members:</th><th style='text-align:left;' >Description</th></tr></thead>
<tbody><tr><td>vertices</td><td style='text-align:left;' >representing by HashMap</td></tr><tr><td>adjacency</td><td style='text-align:left;' >representing by HashMap</td></tr><tr><td>v</td><td style='text-align:left;' >The number of nodes in the graph (int type)</td></tr><tr><td>e</td><td style='text-align:left;' >The number of edges in the graph (int type)</td></tr><tr><td>mc</td><td style='text-align:left;' >The number of operations performed in the graph (int type)</td></tr></tbody>
</table></figure>
<figure><table>
<thead>
<tr><th style='text-align:left;' >Methods:</th><th style='text-align:left;' >Description</th><th>Time complexity</th></tr></thead>
<tbody><tr><td style='text-align:left;' >WGraph_DS</td><td style='text-align:left;' >constructor</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getNode</td><td style='text-align:left;' >return an object of type node_info that associated with the initial key</td><td>O(1)</td></tr><tr><td style='text-align:left;' >hasEdge</td><td style='text-align:left;' >return true or false if there is an edge between two nodes</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getEdge</td><td style='text-align:left;' >return the weight of the edge between two nodes</td><td>O(1)</td></tr><tr><td style='text-align:left;' >addNode</td><td style='text-align:left;' >add a new node to the graph</td><td>O(1)</td></tr><tr><td style='text-align:left;' >Connect</td><td style='text-align:left;' >connect between two nodes in the graph with weight</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getV</td><td style='text-align:left;' >return the collection of all nodes in the graph</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getV</td><td style='text-align:left;' >return a collection of all the neighbors that associated with the initial key.</td><td>O(1)</td></tr><tr><td style='text-align:left;' >removeNode</td><td style='text-align:left;' >remove a node and all the edges that are linked to that node in the graph.</td><td>O(N)</td></tr><tr><td style='text-align:left;' >removeEdge</td><td style='text-align:left;' >remove the edge between two nodes in the graph.</td><td>O(1)</td></tr></tbody>
</table></figure>
<p>	</p>
<ol start='2' >
<li><strong>WGraph_Algo</strong> which implements the interface : <strong>weighted_graph_algorithms</strong></li>

</ol>
<figure><table>
<thead>
<tr><th>Data members</th><th>Description</th></tr></thead>
<tbody><tr><td>graph</td><td>an object (weighted_graph) that represents a graph</td></tr></tbody>
</table></figure>
<figure><table>
<thead>
<tr><th style='text-align:left;' >Methods:</th><th style='text-align:left;' >Description</th><th>Time complexity</th></tr></thead>
<tbody><tr><td style='text-align:left;' >WGraph_Algo</td><td style='text-align:left;' >constructor</td><td>O(1)</td></tr><tr><td style='text-align:left;' >init </td><td style='text-align:left;' >initialize the graph</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getGraph</td><td style='text-align:left;' >return a graph object</td><td>O(1)</td></tr><tr><td style='text-align:left;' >Copy</td><td style='text-align:left;' >return a deep copy of graph object </td><td>O(1)</td></tr><tr><td style='text-align:left;' >isConnected</td><td style='text-align:left;' >return true or false if the graph is connected</td><td><img src="https://wikimedia.org/api/rest_v1/media/math/render/svg/a7cf317fbe3965ae3164f28c1f6858696adb23f4" style="zoom:90%;" /></td></tr><tr><td style='text-align:left;' >shortestPathDist</td><td style='text-align:left;' >return the length (int) of the shortest path between two nodes</td><td><img src="https://wikimedia.org/api/rest_v1/media/math/render/svg/e22162be85d06b346f3b7f7aad9746da0c1019c9" /></td></tr><tr><td style='text-align:left;' >shortestPath</td><td style='text-align:left;' >return a collection that contains all the nodes in the path between two nodes</td><td><img src="https://wikimedia.org/api/rest_v1/media/math/render/svg/e22162be85d06b346f3b7f7aad9746da0c1019c9" style="zoom:90%;" /></td></tr><tr><td style='text-align:left;' >save</td><td style='text-align:left;' >graph object serialization</td><td>&nbsp;</td></tr><tr><td style='text-align:left;' >load</td><td style='text-align:left;' >graph object deserialization</td><td>&nbsp;</td></tr></tbody>
</table></figure>
<p>&nbsp;</p>
<ol start='3' >
<li><strong>Node</strong> which implements the interface : <strong>node_info</strong></li>

</ol>
<figure><table>
<thead>
<tr><th>Data members</th><th>Description</th></tr></thead>
<tbody><tr><td>key</td><td>represent the key of each node (int)</td></tr><tr><td>info</td><td>represent the info of each node (String)</td></tr><tr><td>tag</td><td>represent the weight of each node (Double)</td></tr></tbody>
</table></figure>
<figure><table>
<thead>
<tr><th style='text-align:left;' >Methods:</th><th style='text-align:left;' >Description</th><th>Time complexity</th></tr></thead>
<tbody><tr><td style='text-align:left;' >Node</td><td style='text-align:left;' >constructor</td><td>O(1)</td></tr><tr><td style='text-align:left;' >Node</td><td style='text-align:left;' >copy constructor</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getKey and setKey</td><td style='text-align:left;' >return the key (int) that associated with the node</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getInfo and setInfo</td><td style='text-align:left;' >return the info (String) that associated with the node</td><td>O(1)</td></tr><tr><td style='text-align:left;' >getTag and setTag</td><td style='text-align:left;' >return the info (Double) that associated with the node</td><td>O(1)</td></tr></tbody>
</table></figure>
