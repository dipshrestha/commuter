package com.dipesh.proj;

import com.dipesh.proj.util.CommandRunner;
import com.dipesh.proj.util.GraphBuilder;

import java.util.List;

/**
 *
 * @author dshrestha
 */
public class Main {

    public static void main(String[] args) {

        if (args == null || args.length < 2) {
            System.out.println("Invalid input! Please enter two filename");
            System.exit(1);
        }

        try{
            // Build Graph
            List<Edge> edges = new GraphBuilder().build(args[0]);

            // Create Graph
            Graph g = new MyGraph(edges);

            // Run all commands
            new CommandRunner(args[1]).run(g);

        }catch(Exception e) {
            System.out.println("Invalid input!\n");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
