package com.dipesh.proj.command;

import com.dipesh.proj.Edge;
import com.dipesh.proj.Graph;

import java.util.Collection;
import java.util.List;

/**
 * Find the shortest distance between two nodes
 * @author dshrestha
 */
public class SDistCommand implements Command{

    final String s;
    final String e;

    public SDistCommand(String s, String e) throws Exception {
        if(s == null || e == null || s.isEmpty() || e.isEmpty())
            throw new Exception("Illegal Command");
        this.s = s;
        this.e = e;
    }

    @Override
    public String execute(Graph g) throws Exception {
        int distance = Integer.MAX_VALUE;
        Collection<List<Edge>> trips = g.getAllTrip(s, e);
        for(List<Edge> trip: trips) {
            int temp = 0;
            for(Edge edge: trip) {
                temp += edge.getWeight();
            }
            if(temp < distance) {
                distance = temp;
            }
        }
        if(trips == null || trips.size() < 1){
            return "0";
        }
        return String.valueOf(distance);
    }

}
