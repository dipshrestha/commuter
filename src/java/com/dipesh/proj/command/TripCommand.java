package com.dipesh.proj.command;

import com.dipesh.proj.Edge;
import com.dipesh.proj.Graph;

import java.util.Collection;
import java.util.List;

/**
 * Get trip along with conditions.
 *
 * For instance getting trip from A to C where node hops are 3
 * trip AC node = 3
 *
 * s:A
 * e:C
 * cond1: node
 * cond2: =
 * cond3: 3
 *
 * @author dshrestha
 */
public class TripCommand implements Command{

    final String s;
    final String e;
    final String cond1;
    final String cond2;
    final String cond3;
    Graph g;

    /**
     * Enum for operators
     */
    public enum Operator{
        EQUALS("="), GREATER_THAN(">"), GREATER_THAN_EQUALS(">="),
        SMALLER_THAN("<"),SMALLER_THAN_EQUALS("<="),NONE("");

        private String value;
        private Operator(String value) {
            this.value = value;
        }
        public static Operator find(String s) {
            for (Operator operator : Operator.values()) {
               if(operator.value.equals(s)){
                   return operator;
               }
            }
            return NONE;
        }
    }

    /**
     * Enum for sub commands like "dist", "node"
     */
    public enum SubCommand {
        NODE("node"), DIST("dist"), NONE("");

        private String value;
        private SubCommand(String value) {
            this.value = value;
        }
        public static SubCommand find(String s) {
            for (SubCommand subCmd : SubCommand.values()) {
                if(subCmd.value.equals(s)){
                    return subCmd;
                }
            }
            return NONE;
        }
    }

    public TripCommand(String s, String e, String cond1, String cond2, String cond3)  throws Exception{
        if(s == null || e == null || cond1 == null || cond2  == null || cond3 == null || s.isEmpty() || e.isEmpty() )
            throw new Exception("Illegal Command");

        this.s = s;
        this.e = e;
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.cond3 = cond3;
    }

    @Override
    public String execute(Graph g) throws Exception {
        this.g = g;
        return calculate();
    }

    private String calculate() {
        int count = 0;
        Collection<List<Edge>> trips = g.getAllTrip(s, e);
        Collection<List<Edge>> roundTrips = g.getAllTrip(e, e);

        for(List<Edge> trip: trips) {
            if(!trip.get(0).getFrom().getName().equals(s))
                continue;

            int temp = 0;
            SubCommand subCommand = SubCommand.find(cond1);
            switch (subCommand) {
                case NODE: temp = getNodeCount(trip); break;
                case DIST: temp = getDistance(trip); break;
                default:
            }
            count += calculate(temp);

            if(temp > 0 && !s.equals(e)) {
                for(List<Edge> roundTrip: roundTrips) {
                    if(!roundTrip.get(0).getFrom().getName().equals(e))
                        continue;

                    int temp1 = 0;
                    switch (subCommand) {
                        case NODE: temp1 = getNodeCount(roundTrip); break;
                        case DIST: temp1 = getDistance(roundTrip); break;
                        default:
                    }
                    count += calculate(temp + temp1);
                }
            }
        }

        return String.valueOf(count);
    }

    private int calculate(int temp) {
        Operator oper = Operator.find(cond2);
        int cond3Value = Integer.parseInt(cond3);
        switch (oper) {
            case EQUALS: return (temp == cond3Value)? 1: 0;
            case SMALLER_THAN: return (temp < cond3Value)? 1: 0;
            case SMALLER_THAN_EQUALS: return (temp <= cond3Value)? 1: 0;
            case GREATER_THAN: return (temp > cond3Value)? 1: 0;
            case GREATER_THAN_EQUALS: return (temp >= cond3Value)? 1: 0;
            default:
        }
        return 0;
    }

    private int getDistance(List<Edge> trip) {
        int distance = 0;
        for(Edge e: trip) {
            distance += e.getWeight();
        }
        return distance;
    }

    private int getNodeCount(List<Edge> trip) {
        return trip.size();
    }
}