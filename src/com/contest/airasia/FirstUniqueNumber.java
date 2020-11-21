package com.contest.airasia;

import java.util.LinkedHashMap;
import java.util.Map;

// In a stream of infinite numbers at any point of time, we need to get the first unique number.
// First non-repiting number
// 7 - 7 (7,2)->(11,1)
// 7,11 -7
// 7,11,7 - 11
public class FirstUniqueNumber {

    LinkedHashMap<Integer, Integer> map;

    public FirstUniqueNumber() {
        map = new LinkedHashMap<>();
    }

    void createMap(int[] array) {
        for (int i : array) {
            if (map.containsKey(i)) {
                int count = map.get(i);
                map.put(i, count + 1);
            } else {
                map.put(i, 1);
            }
        }
    }

    // 7,2
    // 11,1
    int firstUniqueNumer(int[] array, int newNumber) {

        if (map.containsKey(newNumber)) {
            int count = map.get(newNumber);
            map.put(newNumber, count + 1);
        } else {
            map.put(newNumber, 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueNumber obj = new FirstUniqueNumber();
        int[] array = new int[] {1, 3, 7};
        obj.createMap(array);
        System.out.println(obj.firstUniqueNumer(array, 1));
        System.out.println(obj.firstUniqueNumer(array, 7));
        System.out.println(obj.firstUniqueNumer(array, 3));
    }

}
