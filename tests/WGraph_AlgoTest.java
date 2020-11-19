package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test used for the interface (weighted_graph_algorithms)
 * and also the class that implement this interface (WGraph_Algo) by using JUNIT.
 * @author dolev abuhazira
 */
class WGraph_AlgoTest {
    weighted_graph graph;
    weighted_graph_algorithms graph_algorithms;


    @Test
    @BeforeEach
    void setUp() {
        this.graph = new WGraph_DS();
        this.graph_algorithms = new WGraph_Algo();
    }
    @DisplayName("This test initialize a graph object which is a weighted_graph_algorithms type")
    @Test
    void init() {
        graph_algorithms.init(graph);
        Assertions.assertEquals(graph,graph_algorithms.getGraph());
    }
    @DisplayName("This method builds a graph for use in tests")
    public void smallGraph() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.connect(1,4,2.11);
        graph.connect(1,3,4.7);
        graph.connect(1,2,1.5);
        graph.connect(2,3,9.11);
        graph.connect(2,6,22.5);
        graph.connect(2,5,47.2);
        graph.connect(3,4,11.6);
        graph.connect(3,5,6.3);
        graph.connect(4,5,5.2);
        graph.connect(5,6,13.4);
    }
    @DisplayName("This method builds a graph with a million vertices and fifty million edges for use in testes")
    @Test
    public void hugeGraph(){
     Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
            for (int i = 1; i <= 1000000; i++) {
                graph.addNode(i);
            }
            Random rnd = new Random(1);
            while (graph.edgeSize() < 1000000 * 5) {
                int a = (int) (rnd.nextDouble() * ((double) 1000000 - (0.0 + 1)) + (0.0 + 1));
                int b = (int) (rnd.nextDouble() * ((double) 1000000 - (0.0 + 1)) + (0.0 + 1));
                int i = graph.getNode(a).getKey();
                int j = graph.getNode(b).getKey();
                graph.connect(i, j, 2 + (10 - 2) * rnd.nextDouble());
            }
        });
    }
    @Test
    @DisplayName("This test checks whether the method returns weighted_graph object type")
    void getGraph() {
        graph.addNode(1);
        graph_algorithms.init(graph);
        int expected = 1;
        int actual = graph_algorithms.getGraph().getNode(1).getKey();
        Assertions.assertEquals(expected,actual);
        Assertions.assertEquals(graph,graph_algorithms.getGraph());
    }
    @DisplayName("This test checks whether two graphs(with million nodes and fifty million edges) are identical")
    @Test
    void copy() {
        hugeGraph();
        graph_algorithms.init(graph);
        weighted_graph actual = graph_algorithms.copy();
        Assertions.assertNotSame(graph,actual); // check whether two graphs object do not refer to the same object.
        Assertions.assertEquals(graph,actual);

    }
    @DisplayName("This test checks whether a sub-graph of a graph is a connective")
    @Test
    void isConnected() {
        smallGraph();
        // Splitting the graph into two connectivity components
        graph.removeNode(1);
        graph.removeNode(3);
        graph_algorithms.init(graph);
        Assertions.assertTrue(graph_algorithms.isConnected());
        graph.removeNode(5);
        Assertions.assertFalse(graph_algorithms.isConnected());
        int expected = 3;
        Assertions.assertEquals(expected,graph_algorithms.getGraph().nodeSize());
    }

    @Test
    @DisplayName("This test checks the shortest path between two nodes in a sub graph")
    void shortestPathDist() {
    smallGraph();
    graph_algorithms.init(graph);
    graph.removeNode(3);
    graph.removeNode(4);
    double expected = 24.0;
    double actual = graph_algorithms.shortestPathDist(1,6);
    Assertions.assertEquals(expected,actual);
    graph.removeNode(6);
    actual = graph_algorithms.shortestPathDist(1,6);
    Assertions.assertEquals(-1,actual);
    double actual_length_after_remove = graph_algorithms.shortestPathDist(1,5);
    double expected_length = 48.7;
    Assertions.assertEquals(expected_length,actual_length_after_remove);
    }


    @DisplayName("This test checks whether the shortest path contains the relevant vertices")
    @Test
    void shortestPath() {
        smallGraph();
        graph_algorithms.init(graph);
        List<node_info> actual_path = new LinkedList<>(graph_algorithms.shortestPath(1,6));
        LinkedList<node_info> expected_path = new LinkedList<>();
        expected_path.add(graph.getNode(1));
        expected_path.add(graph.getNode(4));
        expected_path.add(graph.getNode(5));
        expected_path.add(graph.getNode(6));
        Assertions.assertEquals(expected_path,actual_path);
    }
    @DisplayName("This test checks whether a graph object can be serialized")
    @Test
    void save() throws Exception {
        smallGraph();
        graph_algorithms.init(graph);
        String file_name = "newGraph.bin";
        try {
            graph_algorithms.save(file_name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @DisplayName("This test checks extracting a data structure from a series of bytes (deserialization)")
    @Test
    void load() throws Exception {
        try {
            save();
            weighted_graph_algorithms newgraph = new WGraph_Algo();
            newgraph.load("newGraph.bin");
            Assertions.assertNotSame(graph_algorithms,newgraph);
            Assertions.assertEquals(graph_algorithms,newgraph);
        }
        catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("This test checks if it is possible to reset all data members")
    @Test
    void returns() {
        smallGraph();
        graph_algorithms.init(graph);
        graph_algorithms.shortestPathDist(1,6);
        double actual = graph.getNode(6).getTag();
        double unexpected = Double.MAX_VALUE;
        Assertions.assertNotEquals(unexpected,actual);
        ((WGraph_Algo)graph_algorithms).Returns();
        double expected = Double.MAX_VALUE;
        Assertions.assertNotEquals(unexpected,actual);
    }

}
