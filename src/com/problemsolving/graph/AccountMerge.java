/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author paras.chawla
 * @version $Id: AccountMerge.java, v 0.1 2021-02-24 10:40 AM paras.chawla Exp $$
 * <p>
 * DFS https://leetcode.com/problems/accounts-merge/discuss/109158/Java-Solution-(Build-graph-%2B-DFS-search)
 * <p>
 * 3 Approaches https://leetcode.com/problems/accounts-merge/discuss/109157/JavaC%2B%2B-Union-Find
 * <p>
 * Union Find https://leetcode.com/problems/accounts-merge/discuss/140978/Easy-to-Understand-Union-Find-in-Java-95
 */
public class AccountMerge {

    // Approach 1- Using DFS
    /* accounts[[]] =
     [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"],
      ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
    */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // 1. Keep a track of Email to UserName in a map
        Map<String, String> emailToNameMap = new HashMap<>();

        // 2. Create a Map as a graph, here a vertex is an EmailId
        Map<String, Set<String>> graph = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) {

            String userName = accounts.get(i).get(0);

            // Traverse all emails for current userName
            for (int j = 1; j < accounts.get(i).size(); j++) {

                String currEmail = accounts.get(i).get(j);
                graph.putIfAbsent(currEmail, new HashSet<>());

                // add email to Map as well
                emailToNameMap.put(currEmail, userName);
                // skip first email to connect to previous email since there is no prev emails
                if (j == 1) { continue; }

                String prevEmail = accounts.get(i).get(j - 1);

                // Connect current EmailId to previous emailId
                graph.get(currEmail).add(prevEmail);
                // Connect previous EmailId to current emailId
                graph.get(prevEmail).add(currEmail);
            }
        }

        // Track of visited Emails used in DFS
        Set<String> visitedSet = new HashSet<>();

        // Final Result
        List<List<String>> res = new LinkedList<>();

        // 3. Traverse Graph Using DFS on different components of Graph
        for (String emailId : emailToNameMap.keySet()) {

            List<String> list = new LinkedList<>();
            if (visitedSet.add(emailId)) {
                // after dfs, list contains all emailId in same component in graph
                dfs(graph, emailId, visitedSet, list);

                // Sort EmailIds
                Collections.sort(list);

                // Add userName as first Element in list
                list.add(0, emailToNameMap.get(emailId));

                // Add list to res
                res.add(list);

            }
        }

        return res;
    }

    private void dfs(Map<String, Set<String>> graph, String emailId, Set<String> visitedSet, List<String> list) {
        list.add(emailId);
        for (String childEmail : graph.get(emailId)) {
            if (visitedSet.add(childEmail)) {
                dfs(graph, childEmail, visitedSet, list);
            }
        }
    }

    // Approach 2 - Using Disjoint Set
    // Link - https://leetcode.com/problems/accounts-merge/discuss/109157/JavaC%2B%2B-Union-Find
    public List<List<String>> accountsMerge2(List<List<String>> acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++) { parents.put(find(a.get(i), parents), p); }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) { unions.put(p, new TreeSet<>()); }
            for (int i = 1; i < a.size(); i++) { unions.get(p).add(a.get(i)); }
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }

    private String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p);
    }

    public static void main(String[] args) {
        AccountMerge obj = new AccountMerge();

        List<List<String>> accounts = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("John");
        list1.add("johnsmith@mail.com");
        list1.add("john00@mail.com");

        List<String> list2 = new ArrayList<>();
        list2.add("John");
        list2.add("johnnybravo@mail.com");

        List<String> list3 = new ArrayList<>();
        list3.add("John");
        list3.add("johnsmith@mail.com");
        list3.add("john_newyork@mail.com");

        List<String> list4 = new ArrayList<>();
        list4.add("Mary");
        list4.add("mary@mail.com");

        accounts.add(list1);
        accounts.add(list2);
        accounts.add(list3);
        accounts.add(list4);

        obj.accountsMerge2(accounts);
    }
}