package ex1.tests;

import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {
    weighted_graph graph;

    /**
     * This test used for the interface (weighted_graph)
     * and also the class that implement this interface (WGraph_DS) by using JUNIT.
     * @author dolev abuhazira
     */
    @BeforeEach
    void Initialization() {
        this.graph = new WGraph_DS();
    }

    @DisplayName("This test represent construction graph with million vertices and fifty million edges")
    @Test
    void hugeGraph() {
        String actualResult = Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
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
            return "The construction process was completed successfully!";
       });
        System.out.println(actualResult);
    }

    @DisplayName("This test checks whether it is possible to access a node that does not exist in the graph" )
    @Test
    void getNode() {
        Assertions.assertNull(graph.getNode(1));
        graph.addNode(1);
        Assertions.assertEquals(1,graph.getNode(1).getKey());

    }
    @DisplayName("This test check if there is edge between two nodes whether one of the node are removed")
    @Test
    void hasEdge() {
    graph.addNode(1);
    graph.addNode(2);
    double weight = Math.random()*13.4;
    graph.connect(1,2,weight);
    Assertions.assertTrue(graph.hasEdge(1,2));
    graph.removeNode(1);
    Assertions.assertFalse(graph.hasEdge(1,2));
    }

    @DisplayName("This test checks whether an edge that does not exist in the graph can return its value ")
    @Test
    void getEdge() {
        graph.addNode(1);
        graph.addNode(2);
        double negative_weight = (Math.random()*10) * -1;
        graph.connect(1,2,negative_weight);
        double expected = -1;
        Assertions.assertEquals(expected,graph.getEdge(1,2));
    }

    @DisplayName("This test checks whether an existing node can be added to the graph")
    @Test
    void addNode() {
        graph.addNode(1);
        graph.addNode(1);
        graph.addNode(1);
        int expected = 1;
        Assertions.assertEquals(expected,graph.getNode(1).getKey());
        Assertions.assertEquals(expected,graph.getMC());
        Assertions.assertEquals(expected,graph.nodeSize());
        graph.removeNode(1);
        Assertions.assertNull(graph.getNode(1));
    }

    @DisplayName("This test checks whether a negative weight can be defined as an edge")
    @Test
    void connect() {
        graph.addNode(1);
        graph.addNode(2);
        double negative_weight = (Math.random()*10) * -1;
        graph.connect(1,2,negative_weight);
        Assertions.assertFalse(graph.hasEdge(1,2));
        double positive_weight = (Math.random()*10);
        graph.connect(1,2,positive_weight);
        graph.connect(1,2,positive_weight);
        Assertions.assertTrue(graph.hasEdge(1,2));
        int expected = 3;
        Assertions.assertEquals(expected,graph.getMC());
    }


    @Test
    @DisplayName("This test checks whether the size of the group of nodes is equal to the number of nodes in the graph")
    void getV() {
       int amount_of_nodes = 34;
       int runner = 1;
       while(graph.nodeSize() < amount_of_nodes) {
           graph.addNode(runner++);
       }
       Assertions.assertEquals(graph.nodeSize(),graph.getV().size());
    }

    @Test
    @DisplayName("This test check whether deleting a node causes an unnatural change in the graph")
    void removeNode() {
        hugeGraph();
        int amount_of_neighbours = graph.getV(1).size();
        int amount_of_edges = graph.edgeSize();
        graph.removeNode(1);
        int expected = 0;
        Assertions.assertEquals(expected,graph.getV(1).size());
        Assertions.assertEquals(graph.edgeSize(),amount_of_edges - amount_of_neighbours);
    }
    @DisplayName("This test checks whether an edge that does not exist in the graph can be removed")
    @Test
    void removeEdge() {
        graph.addNode(1);
        graph.addNode(2);
        double positive_weight = (Math.random()*10);
        graph.removeEdge(1,2);
        int expected = 0;
        Assertions.assertEquals(expected,graph.edgeSize());
        graph.connect(1,2,positive_weight);
        Assertions.assertEquals(positive_weight,graph.getEdge(1,2));
    }

    @DisplayName("This test checks whether by adding the same node, the size of the nodes has been changed")
    @Test
    void nodeSize() {
        graph.addNode(1);
        graph.addNode(1);
        graph.addNode(1);
        int expected = 1;
        Assertions.assertEquals(expected,graph.nodeSize());
    }

    @DisplayName("This test checks whether by adding the same edge, the size of the edges has been changed")
    @Test
    void edgeSize() {
        graph.addNode(1);
        graph.addNode(2);
        double positive_weight = (Math.random()*10);
        double negative_weight = ((Math.random()*10)* -1);
        graph.connect(1,2,positive_weight);
        graph.connect(1,2,positive_weight);
        graph.connect(1,2,positive_weight);
        graph.connect(1,2,negative_weight);
        int expected = 1;
        Assertions.assertEquals(expected,graph.edgeSize());
    }
    @DisplayName("This test checks whether the amount of changes in the graph changes when the graph does not change")
    @Test
    void getMC() {
        graph.addNode(1);
        int expected = 1;
        Assertions.assertEquals(expected,graph.getMC());
        graph.addNode(1);
        graph.addNode(1);
        graph.addNode(1);
        graph.addNode(1);
        graph.addNode(1);
        Assertions.assertEquals(expected,graph.getMC());
    }
}