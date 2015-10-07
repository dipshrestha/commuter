package com.dipesh.proj.command;

import com.dipesh.proj.Graph;

/**
 * @author dshrestha
 */
public interface Command {
    String execute(Graph g) throws Exception;
}
