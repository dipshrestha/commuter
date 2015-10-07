package com.dipesh.proj.util;

import com.dipesh.proj.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes the commands from file
 * @author dshrestha
 */
public class CommandRunner {

    String file;

    public CommandRunner(String file) {
        this.file = file;
    }

    public List<String[]> build() throws Exception {
        BufferedReader bReader = new BufferedReader(new FileReader(file));
        String line;

        List<String[]> commands = new ArrayList<>();
        while ((line = bReader.readLine()) != null) {
            String[] parts = line.split(" ", 2);
            if (parts == null || parts.length < 2) {
                System.out.print("Invalid Arguments");
                System.exit(1);
            }

            String c = parts[0];
            String p = parts[1];
            commands.add(new String[]{c, p});
        }
        return commands;
    }

    public void run(Graph g) throws Exception{
        List<String[]> commands = build();
        for(String[] s: commands) {
            runCommand(g, s);
        }
    }

    private void runCommand(Graph g, String[] s) {
        // command
        String c = s[0];

        // nodes + other params
        String p = s[1];

        if(c == null || c.isEmpty()) {
            System.out.print("Invalid Arguments");
            return;
        }

        String[] params = p.split(" ");

        // nodes
        String nodes = params[0];

        // routes
        String[] route = new String[nodes.length()];
        for(int i = 0; i < nodes.length(); i++) {
            route[i] = nodes.substring(i, i+1);
        }

        // analyze the commands
        if(c.equals("dist")) {
            String distance = g.distance(route);
            System.out.println(distance);
        } else if(c.equals("sdist")) {
            String distance = g.shortestDistance(route[0], route[1]);
            System.out.println(distance);
        } else if(c.equals("trip")) {
            String count = g.tripWithCondition(route[0], route[1], params[1], params[2], params[3]);
            System.out.println(count);
        }
    }
}
