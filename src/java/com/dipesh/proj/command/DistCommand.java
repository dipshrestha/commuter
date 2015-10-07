package com.dipesh.proj.command;

import com.dipesh.proj.Graph;

/**
 *
 * Find Distance of the given route
 * @author dshrestha
 *
 */
public class DistCommand implements Command {

    /**
     * String constant used when there is no route between nodes
     */
    public static final String NO_SUCH_ROUTE_ERROR = "NO SUCH ROUTE";

    final private String[] route;

    public DistCommand(String...route) throws Exception {
        if(route == null || route.length < 2) {
            throw new Exception("Illegal Command");
        }
        this.route = route;
    }

    @Override
    public String execute(Graph g) throws Exception {
        int d = 0;
        int temp = 0;
        for(int i = 0; i < route.length - 1;) {
            String s = route[i];
            String e = route[i + 1];
            temp = g.getEdge(s, e).getWeight();
            if(temp == -1) {
                return NO_SUCH_ROUTE_ERROR;
            }
            d += temp;
            i++;
        }

        if(d == -1)
            return NO_SUCH_ROUTE_ERROR;

        return String.valueOf(d);
    }
}
