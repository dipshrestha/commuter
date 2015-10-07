package com.dipesh.proj;

/**
 * Edge is link between two nodes("from", "to") with a weight.
 * Weight is distance between the two nodes which can be a positive integer value.
 * @author dshrestha
 */
public class Edge {
    private Node from;
    private Node to;
    private int weight;

    public Edge(){
        from = new Node();
        to = new Node();
        weight = -1;
    }

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
