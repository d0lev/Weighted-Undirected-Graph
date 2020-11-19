package ex1.src;

import java.io.*;
import java.util.*;


/**
 * This class represents the use of various algorithms and methods made on graphs.
 * @author dolev abuhazira
 */

public class WGraph_Algo implements weighted_graph_algorithms,Serializable {
    weighted_graph graph;

    /**
     * A default constructor
     */
    public WGraph_Algo() {this.graph = new WGraph_DS();}

    /**
     * A method that initialize the graph with given a graph object (weighted_graph).
     * @param g is the graph in which the various methods and algorithms will be performed.
     */
    @Override
    public void init(weighted_graph g) {this.graph = g;}

    /**
     * A method that return a graph.
     * @return graph object (weighted_graph).
     */
    @Override
    public weighted_graph getGraph() { return this.graph;}

    /**
     * A deep copy method.
     * This method copies all the nodes in the graph,include the edges and the value of the edges.
     * @return a new graph (weighted_graph) with same details but different reference.
     */
    @Override
    public weighted_graph copy() {
        WGraph_DS graphcopy = new WGraph_DS();
        for (node_info runner : graph.getV()) {
                graphcopy.addNode(runner);
            for(node_info neighbour : graph.getV(runner.getKey())) {
                double weight = graph.getEdge(runner.getKey(), neighbour.getKey());
                graphcopy.connect(runner.getKey(), neighbour.getKey(),weight);
            }
        }
        return graphcopy;
    }

    /**
     * A method that checks whether the graph is connected.
     * @Note: This method implements the bfs algorithm by traverse the graph and mark each node,
     * and adding all the marked nodes to HashSet.
     * if the size of the set is equals to the amount nodes in the graph - this graph is connected.
     * @return true if and only if there is a valid path from every node to each other node.
     */
    @Override
    public boolean isConnected() {
        if(graph.nodeSize() == 0 || graph.nodeSize() == 1) return true;
        Queue<node_info> queue = new LinkedList<>();
        HashSet<node_info> vertices = new HashSet<>();
        node_info first = graph.getV().stream().findFirst().get();
        first.setTag(1);
        queue.add(first);
        vertices.add(first);
        while(!queue.isEmpty()) {
            node_info w = queue.poll();
            w.setTag(1);
            for(node_info runner : graph.getV(w.getKey())) {
                if(runner.getTag() == Double.MAX_VALUE) {
                    runner.setTag(1);
                    queue.add(runner);
                    vertices.add(runner);
                }
            }
        }
        Returns();
        if(vertices.size() == graph.nodeSize()) return true;
        return false;
    }

    /**
     * A method that return the length of the shortest path between two nodes.
     *@Note: This method implements the Dijkstra algorithm by mark the nodes,
      and also keep on each node the shortest path from the source node.
      These nodes enter a priority queue and the nodes that poll from the queue will be
      the nodes with the shortest distance (by using a comparator) and also they will marked.
      it follows that the destination node will keep the shortest distance from the source node.
     * @param src - start (source) node
     * @param dest - end (destination) node
     * @return the tag that keep the destination node.
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        node_info source = graph.getNode(src);
        node_info destination = graph.getNode(dest);
        if(source == destination) return  0;
        if(graph.getV().contains(source) && graph.getV().contains(destination) && source != destination) {
            Returns();
            PriorityQueue<node_info> pqueue = new PriorityQueue<>(new Comparator());
            source.setTag(0);
            pqueue.add(source);
            while (!pqueue.isEmpty()) {
                node_info w = pqueue.poll();
                w.setInfo("visited");
                for (node_info runner : graph.getV(w.getKey())) {
                    if (runner.getInfo() == "unvisited") {
                        double weight = w.getTag() + graph.getEdge(w.getKey(), runner.getKey());
                        if (weight < runner.getTag()) {
                            runner.setTag(weight);
                            pqueue.add(runner);
                        }
                    }
                }
            }
            return destination.getTag();
        }
        return -1;
    }

    /**
    *A method that return the path of the shortest path between two nodes.
    *@Note: This method implements the Dijkstra algorithm just like the method above implements.
    After the nodes kept the shortest distance from the source node,
    they enter a stack by a loop that starts at the destination node and ends at the source node.
    each node that enters the stack must have a distance that corresponding to that of the previous one.
    and finally those nodes are goes inside a list from beginning to the end by (LIFO).
     * @param src - start (source) node
     * @param dest - end (destination) node
     * @return a linkedlist that contain the correct path that start with the source node,
       and end with the destination node.
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        node_info source = graph.getNode(src);
        node_info destination = graph.getNode(dest);
        if(graph.getV().contains(source) && graph.getV().contains(destination) && source != destination) {
            Returns();
            PriorityQueue<node_info> pqueue = new PriorityQueue<>(new Comparator());
            pqueue.add(source);
            source.setTag(0);
            while(!pqueue.isEmpty()) {
                node_info w = pqueue.poll();
                w.setInfo("visited");
                for(node_info runner : graph.getV(w.getKey())) {
                    if(runner.getInfo() == ("unvisited")) {
                        double weight = w.getTag() + graph.getEdge(w.getKey(),runner.getKey());
                        if(weight < runner.getTag()) {
                            runner.setTag(weight);
                            pqueue.add(runner);
                        }
                    }
                }
            }
            // if there is no path from source to destination:
            if(destination.getTag() == Double.MAX_VALUE) return null;
            node_info runner = destination;
            Queue<node_info> queue = new LinkedList<>();
            Stack<node_info> stack = new Stack<>();
            List<node_info> path = new LinkedList<>();
            stack.push(destination);
            while(runner != source) {
                for(node_info current : graph.getV(runner.getKey())) {
                    double weight = current.getTag() + graph.getEdge(current.getKey(),runner.getKey());
                    if(runner.getTag() == weight) {
                        queue.add(current);
                        stack.add(current);
                        break;
                    }
                }
                runner = queue.poll();
            }
            // now the stack is full by reverse order of the path
            while(!stack.isEmpty()) {
                path.add(stack.pop());
            }
            return path;
        }
        return null;
    }

    /**
     * A method that performs graph object serialization.
     * @param file - the file name (may include a relative path).
     * @return true if and only if the graph stored.
     */
    @Override
    public boolean save(String file) {
    try {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.graph);
        oos.close();
        fos.close();
        return true;
    }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * A method that preforms graph object deserialization.
     * @param file - file name (may include a relative path).
     * @return true if and only if the graph is load in successful way.
     */
    @Override
    public boolean load(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            graph = (weighted_graph) ois.readObject();
            ois.close();
            fis.close();
            return true;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * A method that resets all data members.
     */
    public void Returns() {
        for(node_info runner : graph.getV()) {
            runner.setInfo("unvisited");
            runner.setTag(Double.MAX_VALUE);
        }
    }
    /**
     * Override the default method :equals.
     * This method uses in order to perform an equality test between two weighted_graph objects in the most effective way.
     * @param o Is the weighted_graph object which the method makes a comparison.
     * @return true if and only if the objects are indeed the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_Algo that = (WGraph_Algo) o;
        return Objects.equals(graph, that.graph);
    }

    /**
     * Override the default method :hashCode.
     * @Note: if this method returning different values it can improve the performance of the data structure
      that using hashing.
     * @return hashCode value, If two objects are equal by equals then hashCode will return the same value for them.
     */
    @Override
    public int hashCode() {
        return Objects.hash(graph);
    }

    /**
     * A class that implements the interface Comparator
     */
    private static class Comparator implements java.util.Comparator<node_info>, Serializable {
        /**
         * A compare method.
         * This comparator makes sure which node has the smallest distance by using the compare method.
         its follows that this comparator used to control the order of the priority queue that use in the algorithms.
         * @param o1 the first node_info
         * @param o2 the second node_info
         * @return int value : (if o1.tag == o2.tag then return 0 ,
           if o1.tag < o2.tag then return -1 , if o1.tag > o2.tag the return 1).
         */
        @Override
        public int compare(node_info o1, node_info o2) {
            return Double.compare(o1.getTag(), o2.getTag());
        }
    }
}
