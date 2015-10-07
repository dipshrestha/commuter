package com.dipesh.proj.command;

import com.dipesh.proj.Edge;
import com.dipesh.proj.Graph;
import com.dipesh.proj.MyGraph;
import com.dipesh.proj.Node;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DistCommandTest {

    private static Graph g;

    @BeforeClass
    public  static void oneTimeSetUp() {
        List<Edge> edgesList = new ArrayList<>();
        edgesList.add(new Edge(new Node("A"), new Node("B"), 5));
        edgesList.add(new Edge(new Node("B"), new Node("C"), 3));
        edgesList.add(new Edge(new Node("B"), new Node("D"), 1));
        edgesList.add(new Edge(new Node("D"), new Node("A"), 1));
        g = new MyGraph(edgesList);
    }

    @AfterClass
    public static void oneTimeTearDown() {
        g = null;
    }

    @Test
    public void testForExistingRoute3Nodes() throws Exception{
        String[] route = {"A","B","D"};
        Command cmd = new DistCommand(route);

        String expected = "6";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void testForIllegalCommand() throws Exception{
        String[] route = {"A"};
        Command cmd = new DistCommand(route);
    }

    @Test
    public void testForExistingRoute2Nodes() throws Exception{
        String[] route = {"D", "A"};
        Command cmd = new DistCommand(route);

        String expected = "1";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

    @Test
    public void testForNonExistingRoute() throws Exception{
        String[] route = {"A","X","J"};
        Command cmd = new DistCommand(route);

        String expected = DistCommand.NO_SUCH_ROUTE_ERROR;
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

}