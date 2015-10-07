package com.dipesh.proj;

import com.dipesh.proj.command.Command;
import com.dipesh.proj.command.DistCommand;
import com.dipesh.proj.command.SDistCommand;
import com.dipesh.proj.command.TripCommand;

import java.util.*;

/**
 * @author dshrestha
 */
public class MyGraph extends Graph {
    /**
     * Original list of edges
     */
    List<Edge> edgeList = new ArrayList<>();

    /**
     * Map of node along with all adjacent edges
     */
    Map<Node, List<Edge>> adjList = new HashMap<>();

    /**
     * List of visited node in current run
     */
    List<Node> visitedList = new ArrayList<>();

    /**
     * List of visited edges in current run
     */
    List<Edge> visitedEdgeList = new ArrayList<>();

    /**
     * List of all the edges
     */
    List<List<Edge>> allPath = new ArrayList<>();

    public MyGraph(List<Edge> edges) {
        super();
        this.edgeList = edges;
        init();
    }

    private void init() {
        if (edgeList == null || edgeList.isEmpty()) {
            System.out.println("Invalid Input List!");
            return;
        }
        for (Edge edge : edgeList) {
            Node s = edge.getFrom();
            List<Edge> fromHash = adjList.get(s);
            if (fromHash == null) {
                // add element to new List
                List<Edge> newList = new ArrayList<>();
                newList.add(edge);

                // add list to the hash
                adjList.put(s, newList);
            } else {
                // already existing so update list
                fromHash.add(edge);
            }

            Node e = edge.getTo();
            List<Edge> toHash = adjList.get(e);
            if(toHash == null) {
                // add empty list
                adjList.put(e,  new ArrayList<Edge>());
            }
        }
    }

    @Override
    protected Node getNode(String n) {
        Node ret = new Node();
        for (Map.Entry<Node, List<Edge>> entry : adjList.entrySet())
        {
            if(entry.getKey().getName().equals(n))
                ret = entry.getKey();
        }
        return ret;
    }

    @Override
    public List<Edge> getEdges(String n) {
        return adjList.get(new Node(n));
    }

    @Override
    public Edge getEdge(String s, String e) {
        Edge ret = new Edge();
        List<Edge> fromHash = adjList.get(new Node(s));
        for (Edge edge : fromHash) {
            if(edge.getTo().getName().equals(e)){
                ret = edge;
                break;
            }
        }
        return ret;
    }

    @Override
    public Collection<List<Edge>> getAllTrip(String s, String e) {
        allPath = new ArrayList<>();
        Node start = getNode(s);
        Node end = getNode(e);
        if(!(start == null || end == null || start.isEmpty() || end.isEmpty())) {
            calculateAllTrip(start, end);
        }

        return allPath;
    }

    private void calculateAllTrip(Node start, Node end) {

        // check for visited
        if (visitedList.contains(start)) {
            // already visited so return
            return;
        }

        // find from hash
        List<Edge> fromHash = adjList.get(start);
        if (fromHash == null) {
            // no children
            return;
        }

        // set as visited
        visitedList.add(start);

        // for each child
        for (Edge o : fromHash) {
            visitedEdgeList.add(o);

            if (o.getTo().equals(end)) {
                visitedList.add(end);

                Edge[] e = new Edge[visitedEdgeList.size()];
                visitedEdgeList.toArray(e);
                allPath.add(Arrays.asList(e));

                // remove from visited
                visitedList.remove(end);
                visitedEdgeList.remove(o);

            } else {
                calculateAllTrip(o.getTo(), end);
                visitedList.remove(end);
                visitedEdgeList.remove(o);
            }

        }
        // remove from visited
        visitedList.remove(start);
    }

    @Override
    public String shortestDistance(String s, String e) {
        try{
            Command cmd = new SDistCommand(s, e);
            return cmd.execute(this);
        }catch(Exception ex) {
            System.out.println(ex);
        }
        return "";
    }

    @Override
    public String distance(String... s) {
        try{
            Command cmd = new DistCommand(s);
            return cmd.execute(this);
        }catch(Exception ex) {
            System.out.println(ex);
        }
        return "";
    }

    @Override
    public String tripWithCondition(String s, String e, String cond1, String cond2, String cond3) {
        try{
            Command cmd = new TripCommand(s, e, cond1, cond2, cond3);
            return cmd.execute(this);
        }catch(Exception ex) {
            System.out.println(ex);
        }
        return "";
    }
}
