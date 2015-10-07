package com.dipesh.proj.command;

import com.dipesh.proj.Edge;
import com.dipesh.proj.Graph;
import com.dipesh.proj.MyGraph;
import com.dipesh.proj.Node;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TripCommandTest {

    private static Graph g;

    @BeforeClass
    public  static void oneTimeSetUp() {
        List<Edge> edgesList = new ArrayList<>();
        // A-08->C
        // A-05->B-10->C
        //       B-02->D
        // A-02->D-05->C
        edgesList.add(new Edge(new Node("A"), new Node("C"), 8));
        edgesList.add(new Edge(new Node("A"), new Node("B"), 5));
        edgesList.add(new Edge(new Node("B"), new Node("C"), 10));
        edgesList.add(new Edge(new Node("B"), new Node("D"), 2));
        edgesList.add(new Edge(new Node("A"), new Node("D"), 2));
        edgesList.add(new Edge(new Node("D"), new Node("C"), 5));

        g = new MyGraph(edgesList);
    }

    @AfterClass
    public static void oneTimeTearDown() {
        g = null;
    }

    @Test
    public void testForExistingRouteWithNodeEqual() throws Exception{
        String[] params = {"A", "C", "node", "=", "2"};
        Command cmd = new TripCommand(params[0],params[1], params[2], params[3], params[4]);

        String expected = "2";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

    @Test
    public void testForExistingRouteWithDistanceLessThan() throws Exception{
        String[] params = {"A", "C", "dist", "<", "10"};
        Command cmd = new TripCommand(params[0],params[1], params[2], params[3], params[4]);

        // A-08>C, A-02->D-07->C
        String expected = "2";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

    @Test
    public void testForExistingRouteWithDistanceEqual() throws Exception{
        String[] params = {"A", "C", "dist", "=", "7"};
        Command cmd = new TripCommand(params[0],params[1], params[2], params[3], params[4]);

        String expected = "1";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

    @Test
    public void testForNonExistingRoute() throws Exception{
        String[] params = {"A", "X", "node", "=", "7"};
        Command cmd = new TripCommand(params[0],params[1], params[2], params[3], params[4]);

        String expected = "0";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void testForIllegalCommand() throws Exception{
        String[] params = {null, "X", "node", "=", "2"};
        Command cmd = new TripCommand(params[0],params[1], params[2], params[3], params[4]);
    }
}