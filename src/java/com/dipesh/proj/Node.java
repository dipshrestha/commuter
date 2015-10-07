package com.dipesh.proj;

/**
 * Node is any endpoint in the graph with a name.
 * @author dshrestha
 */
public class Node {
    private String name;

    public Node() {
        name = "";
    }

    public Node(String s) {
        name  = s;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;
        return name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isEmpty() {
        if(name == null || name.isEmpty()){
            return true;
        }
        return false;
    }
}
