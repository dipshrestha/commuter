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
import static org.junit.Assert.assertTrue;

public class SDistCommandTest {

    private static Graph g;

    @BeforeClass
    public  static void oneTimeSetUp() {
        List<Edge> edgesList = new ArrayList<>();
        // A-08->C
        // A-05->B-10->C
        //       B-02->D
        edgesList.add(new Edge(new Node("A"), new Node("C"), 8));
        edgesList.add(new Edge(new Node("A"), new Node("B"), 5));
        edgesList.add(new Edge(new Node("B"), new Node("C"), 10));
        edgesList.add(new Edge(new Node("B"), new Node("D"), 2));
        g = new MyGraph(edgesList);
    }

    @AfterClass
    public static void oneTimeTearDown() {
        g = null;
    }

    @Test
    public void testForExistingRoute() throws Exception{
        Command cmd = new SDistCommand("A", "C");

        String expected = "8";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }


    @Test
    public void testForNonExistingRoute() throws Exception{
        Command cmd = new SDistCommand("A", "X");

        String expected = "0";
        String actual = cmd.execute(g);
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void testForIllegalCommand() throws Exception{
        Command cmd = new SDistCommand("A",null);
    }
}