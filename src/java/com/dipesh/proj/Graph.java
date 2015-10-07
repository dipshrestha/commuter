package com.dipesh.proj;

import java.util.Collection;
import java.util.List;

/**
 * Abstract Graph which allows following functionality
 * 1) Search
 * 2) Find distance
 * 3) Find trips
 *
 * @author dshrestha
 */
public abstract class Graph {
    public Graph() {}

    /**
     * Get the node with name
     * @param n name
     * @return node
     */
    protected abstract Node getNode(String n);

    /**
     * Get all edges connecting to this node
     * @param n
     * @return list of edges
     */
    public abstract List<Edge> getEdges(String n);

    /**
     * Get the edge with start node and end node
     * @param s start node
     * @param e end node
     * @return edge
     */
    public abstract Edge getEdge(String s, String e);

    /**
     * Get all the trips between the start node and end node
     * @param s start node
     * @param e end node
     * @return all path
     */
    public abstract Collection<List<Edge>> getAllTrip(String s, String e);

    /**
     * Get the shortest distance between two nodes
     * @param s
     * @param e
     * @return
     */
    public abstract String shortestDistance(String s, String e);

    /**
     * Get the distance of the route
     * @param s
     * @return
     */
    public abstract String distance(String...s);

    /**
     * Get number of trips between two nodes that match condition
     *
     * @param s
     * @param e
     * @param cond1
     * @param cond2
     * @param cond3
     * @return
     */
    public abstract String tripWithCondition(String s, String e, String cond1, String cond2, String cond3);
}
