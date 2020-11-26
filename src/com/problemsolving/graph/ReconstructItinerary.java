/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: ReconstructItinerary.java, v 0.1 2020-10-05 21:29 paras.chawla Exp $$
 */
public class ReconstructItinerary {

    LinkedList<String> result = new LinkedList<String>();
    private int numTickets     = 0;
    private int numTicketsUsed = 0;

    public List<String> findItinerary(List<List<String>> tickets) {

        // 0. base condition
        if (tickets == null || tickets.size() == 0) { return result; }

        numTickets = tickets.size();

        // 1. construct graph
        HashMap<String, PriorityQueue<String>> graph = constructGraph(tickets);

        result.add("JFK");

        // 2. traverse graph starting from "JFK"
        Iterator it = graph.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, PriorityQueue<String>> pair = (Map.Entry) it.next();
            if (pair.getKey().equals("JFK")) {
                //dfs(pair.getKey(), graph);
                dfsBackTrack(pair.getKey(), graph);
                break;
            }
        }

        return this.result;
    }

    private void dfs(String currAirport, HashMap<String, PriorityQueue<String>> graph) {

        if (graph.containsKey(currAirport)) {
            PriorityQueue<String> destAirport = graph.get(currAirport);
            while (!destAirport.isEmpty()) {
                // while we visit the edge, we trim it off from graph.
                String dest = destAirport.poll();
                dfs(dest, graph);
            }
        }
        this.result.offerFirst(currAirport);
    }

    private void dfsBackTrack(String currAirport, HashMap<String, PriorityQueue<String>> graph) {
        // base case: vertex v is not in adjacency list
        // v is not a starting point in any itinerary, or we would have stored it
        // thus we have reached end point in our DFS
        if (!graph.containsKey(currAirport)) { return; }
        PriorityQueue<String> destAirport = graph.get(currAirport);

        while (!destAirport.isEmpty()) {
            // while we visit the edge, we trim it off from graph.
            String dest = destAirport.poll();
            result.add(dest);
            numTicketsUsed++;

            // keep doing recursion until meet base condition
            dfsBackTrack(dest, graph);

            // we only return when we have used all tickets
            if (numTickets == numTicketsUsed) { return; }

            // otherwise we need to revert the changes and try other tickets
            destAirport.offer(dest);
            // This line took me a long time to debug
            // we must remove the last airport, since in an itinerary, the same airport can appear many times!!
            result.removeLast();
            numTicketsUsed--;
        }

    }

    //Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    private HashMap<String, PriorityQueue<String>> constructGraph(List<List<String>> tickets) {

        HashMap<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> list : tickets) {
            // if not already added in Map
            if (!graph.containsKey(list.get(0))) {
                PriorityQueue set = new PriorityQueue();
                set.add(list.get(1));
                graph.put(list.get(0), set);
            } else {
                PriorityQueue existingSet = graph.get(list.get(0));
                existingSet.add(list.get(1));
                graph.put(list.get(0), existingSet);
            }
        }
        return graph;
    }

    // List<List<String>> tickets - [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    // List<List<String>> tickets - [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]

    public static void main(String[] args) {
        ReconstructItinerary obj = new ReconstructItinerary();
        List<List<String>> tickets = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<String> list = new ArrayList<>();
            tickets.add(list);
        }
        /*
        tickets.get(0).add("JFK");
        tickets.get(0).add("SFO");
        tickets.get(1).add("JFK");
        tickets.get(1).add("ATL");
        tickets.get(2).add("SFO");
        tickets.get(2).add("ATL");
        tickets.get(3).add("ATL");
        tickets.get(3).add("JFK");
        tickets.get(4).add("ATL");
        tickets.get(4).add("SFO");

        tickets.get(0).add("MUC");
        tickets.get(0).add("LHR");
        tickets.get(1).add("JFK");
        tickets.get(1).add("MUC");
        tickets.get(2).add("SFO");
        tickets.get(2).add("SJC");
        tickets.get(3).add("LHR");
        tickets.get(3).add("SFO");
        */
        tickets.get(0).add("JFK");
        tickets.get(0).add("KUL");
        tickets.get(1).add("JFK");
        tickets.get(1).add("NRT");
        tickets.get(2).add("NRT");
        tickets.get(2).add("JFK");

        obj.findItinerary(tickets);
    }
}