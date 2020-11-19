package ex1.src;

import java.io.Serializable;
import java.util.*;

    /**
     * This class is a class that represents an unintentional and unweighted graph
     * @author dolev abuhazira
     */
public class WGraph_DS implements weighted_graph ,Serializable{
    public HashMap<Integer,node_info> vertices;
    public HashMap<Integer,HashMap<node_info,Double>> adjacency;
    public int v;
    public int e;
    public int  mc;

    /**
     * A constructor that initialize the graph by using :
     * HashMap that represent the vertices on the graph.
     * HashMap that represent the neighbours of each node and also the weight of the edge (double)
     */
    public WGraph_DS() {
        this.vertices = new HashMap<Integer, node_info>();
        this.adjacency = new HashMap<Integer, HashMap<node_info, Double>>();
        this.v = 0;
        this.e = 0;
        this.mc =0;
    }

    /**
     * A method that returns the node associated with a specific key.
     * @param key - the key of the node.
     * @return the node_data by the key.
     */
    @Override
    public node_info getNode(int key) {
        if(vertices.containsKey(key)) {
            return vertices.get(key);
        }
        return null;
    }

    /**
     * A method that check if there is an edge between node1 and node2.
     * @param node1
     * @param node2
     * @return true if and only if node1 and node2 are neighbours.
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(adjacency.containsKey(node1) && adjacency.containsKey(node2) && node1 != node2) {
            node_info n2 = getNode(node2);
            if(adjacency.get(node1).containsKey(n2)) return true;
        }
        return false;
    }

    /**
     * A method that return the weight of the edge between two nodes that associated with the given keys.
     * @param node1 is the key that associate with the first node.
     * @param node2 is the key that associate with the second node.
     * @return the weight (double) if and only if there is an edge between node1 and node2
     */
    @Override
    public double getEdge(int node1, int node2) {
        if(hasEdge(node1,node2)) {
            node_info n2 = getNode(node2);
            return adjacency.get(node1).get(n2);
        }
        return -1;
    }

    /**
     * A method that add a node to the graph.
     * @param key is the key that associate with the node.
     */
    @Override
    public void addNode(int key) {
        if(!vertices.containsKey(key)) {
            vertices.put(key, new Node(key));
            adjacency.put(key,new HashMap<>());
            v++;
            mc++;
        }
    }

    /**
     * A method that add a node to the graph.
     * This method uses another sub-class (Node) method (copy constructor)
     *  @param from is the node that the method copying.
     */
    public void addNode(node_info from) {
        if(!vertices.containsKey(from.getKey()) && !vertices.containsKey(from.getKey())) {
            node_info newnode = new Node(from);
            vertices.put(newnode.getKey(), newnode);
            adjacency.put(newnode.getKey(), new HashMap<>());
            v++;
            mc++;
        }
    }

    /**
     * A method that connect an edge between node1 and node2, with an edge with positive weight.
     * @param node1 the specific key that associated for the first node.
     * @param node2 the specific key that associated for the second node.
     * @param w is the positive weight (double)
     * @Note: if there is an edge between node1 and node2, the method updates the weight of the edge.
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if(hasEdge(node1,node2) && w >=0) {
            if(w == getEdge(node1,node2)) return;
            node_info n1 = getNode(node1);
            node_info n2 = getNode(node2);
            adjacency.get(node1).put(n2,w);
            adjacency.get(node2).put(n1,w);
            mc++;
        }
        if (vertices.containsKey(node1) && vertices.containsKey(node2) && !hasEdge(node1, node2) && node1 != node2 && w >= 0) {
            node_info n1 = getNode(node1);
            node_info n2 = getNode(node2);
            adjacency.get(node1).put(n2,w);
            adjacency.get(node2).put(n1,w);
            e++;
            mc++;
        }
    }

    /**
     * This method return a pointer (shallow copy) for a collection representing all the nodes in the graph.
     * @return ArrayList that contain al the vertices in the graph.
     */
    @Override
    public Collection<node_info> getV() {
        return new ArrayList<>(vertices.values());
    }

    /**
     * This method returns a collection containing all the neighbours of the node.
     * @param node_id is the key that associate with the node.
     * @return HashSet that contain all the neighbours.
     * @Note : The time complexity of this method is O(K) where k is the amount of the neighbours.
     */

    @Override
    public Collection<node_info> getV(int node_id) {
        if (adjacency.containsKey(node_id)){
            return new HashSet<>(adjacency.get(node_id).keySet());
        }
        return new HashSet<>();
    }

    /**
     * A method that remove a node that associate with the given key.
     * also this method removes all the edges that connect with this node.
     * @param key is the key that associate with the node.
     * @return the node (node_info type)
     */
    @Override
    public node_info removeNode(int key) {
        node_info n1 = getNode(key);
        if (vertices.containsValue(n1)) {
            for (node_info runner : getV(key)) {
                removeEdge(runner.getKey(), n1.getKey());
            }
            v--;
            mc++;
            adjacency.remove(key);
            return vertices.remove(key);
        }
        return null;

    }

    /**
     * A method that removes an edge between two nodes that associated with the given keys.
     * @param node1 is the key that associate with the first node.
     * @param node2 is the key that associate with the second node.
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (hasEdge(node1,node2)) {
            node_info n1 = getNode(node1);
            node_info n2 = getNode(node2);
            adjacency.get(node1).remove(n2);
            adjacency.get(node2).remove(n1);
            e--;
            mc++;
        }
    }


    @Override
    public int nodeSize() {
        return this.v;

    }

    @Override
    public int edgeSize() {
        return this.e;
    }

    @Override
    public int getMC() {
        return this.mc;
    }

    /**
     * This sub-class represents a node that will be in the graph.
     */
    private class Node implements node_info , Serializable{
        public int key;
        public String info;
        public double tag;

        /**
         * A constructor that initialize the node by given value.
         * @param value is the key that associated with the new node.
         */
        public Node(int value) {
            this.key = value;
            this.info = "unvisited";
            this.tag = Double.MAX_VALUE;
        }

        /**
         * A copy constructor
         * @param from is the node that the method copied his data members.
         */
        public Node(node_info from) {
            this.key = from.getKey();
            this.tag = from.getTag();
            this.info = from.getInfo();
        }

        @Override
        public int getKey() {
            return this.key;
        }

        @Override
        public String getInfo() {
            return this.info;
        }

        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        @Override
        public double getTag() {
            return this.tag;
        }

        @Override
        public void setTag(double t) {
            this.tag = t;
        }

        /**
         * Override the default method :equals.
         * This method uses in order to perform an equality test between two node_info objects in the most effective way.
         * @param o Is the node with which the method makes a comparison.
         * @return true if and only if the objects are indeed the same.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key == node.key &&
                    Double.compare(node.tag, tag) == 0 &&
                    Objects.equals(info, node.info);
        }

        /**
         * Override the default method :hashCode.
         * @return hashCode value, If two objects are equal by equals then hashCode will return the same value for them.
         */
        @Override
        public int hashCode() {
            return Objects.hash(key);
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
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        return v == wGraph_ds.v &&
                e == wGraph_ds.e &&
                Objects.equals(vertices, wGraph_ds.vertices) &&
                Objects.equals(adjacency, wGraph_ds.adjacency);
    }

        /**
         * Override the default method :hashCode.
         * @Note: if this method returning different values it can improve the performance of the data structure
           that using hashing.
         * @return hashCode value, If two objects are equal by equals then hashCode will return the same value for them.
         */
    @Override
    public int hashCode() {
        return Objects.hash(vertices, adjacency, v, e, mc);
    }
}