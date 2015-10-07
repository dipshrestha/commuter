package com.dipesh.proj.util;

import com.dipesh.proj.Edge;
import com.dipesh.proj.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class parse given file and returns list of edges
 * @author dshrestha
 */
public class GraphBuilder {

    public List<Edge> build(String dataFileName) throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader(dataFileName));
        String line;
        List<String> edges = new ArrayList<>();
        while ((line = bReader.readLine()) != null) {
            String[] parts = line.split(",");
            edges.addAll(Arrays.asList(parts));
        }
        bReader.close();
        return create(edges);
    }

    private List<Edge> create(List<String> edges) {
        List<Edge> edgeList = new ArrayList<>();

        if (edges == null || edges.isEmpty()) {
            System.out.println("Invalid Input List!");
            return edgeList;
        }

        for (String e : edges) {
            e = e.trim();
            Node prev = new Node(e.substring(0,1));
            Node curr = new Node(e.substring(1, 2));
            int weight = Integer.parseInt(e.substring(2));

            // create edge
            Edge edge = new Edge(prev, curr, weight);
            edgeList.add(edge);
        }

        return edgeList;
    }
}
