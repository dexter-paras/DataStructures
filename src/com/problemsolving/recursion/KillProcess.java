/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: KillProcess.java, v 0.1 2021-02-22 2:25 PM paras.chawla Exp $$
 */
public class KillProcess {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {

        List<Integer> result = new ArrayList<>();

        // Convert to Map
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < ppid.size(); i++) {

            if (!map.containsKey(ppid.get(i))) {
                map.put(ppid.get(i), new ArrayList<>());
            }
            map.get(ppid.get(i)).add(pid.get(i));
        }

        bfs(map, kill, result);

        return result;
    }

    private void bfs(Map<Integer, List<Integer>> map, int target, List<Integer> result) {

        result.add(target);

        List<Integer> childProcess = map.get(target);//10,15,25,35

        for (int i = 0; childProcess!=null && i < childProcess.size(); i++) {
            bfs(map, childProcess.get(i), result);
        }
    }

    public static void main(String[] args) {
        KillProcess obj = new KillProcess();

        List<Integer> pid = new ArrayList<>();
        List<Integer> ppid = new ArrayList<>();

        pid.add(1);
        pid.add(3);
        pid.add(10);
        pid.add(5);

        ppid.add(3);
        ppid.add(0);
        ppid.add(5);
        ppid.add(3);

        System.out.println(obj.killProcess(pid, ppid, 3));

    }

}



