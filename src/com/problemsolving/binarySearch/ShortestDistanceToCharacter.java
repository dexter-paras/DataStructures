/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: ShortestDistanceToCharacter.java, v 0.1 2021-02-08 8:05 AM paras.chawla Exp $$
 * <p>
 * <p>
 * Input: s = "loveleetcode", c = "e" Output: [3,2,1,0,1,0,0,1,2,2,1,0]
 * <p>
 * Input: s = "aaab", c = "b" Output: [3,2,1,0]
 */
public class ShortestDistanceToCharacter {

    public int[] shortestToChar(String str, char c) {

        // 1. Create an List of integer having char c in String str
        List<Integer> charIdxList = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                charIdxList.add(i);
            }
        }

        // chIdx =[3,5,6,11]

        // 2. Traverse String and find nearest index in list
        int[] result = new int[str.length() - 1];

        for (int i = 0; i < str.length(); i++) {

            int nearestIdx = binarySearch(i, charIdxList, 0, charIdxList.size() - 1);
            result[i] = nearestIdx - i;
        }

        return result;
    }

    private int binarySearch(int target, List<Integer> list, int low, int high) {

        // 1. Corner cases
        if (target < list.get(low)) {
            return list.get(low);
        }

        if (target > list.get(high)) { return list.get(high); }

        int n = list.size();
        int mid=0;
        // Doing Binary Search
        while (low < high) {

            mid = low + (high - low) / 2;

            if (list.get(mid) == target) {
                return mid;
            }

            // target less than arr[mid]
            if (target < list.get(mid)) {

                // target lies btw mid and mid-1
                if (mid > 0 && target > list.get(mid-1))
                    return getClosest(list.get(mid),list.get(mid-1),target);

                // Repeat for left half
                high=mid;
            }
            // target greater than arr[mid]
            else{

                if(mid < n-1 && target < list.get(mid+1))
                    return getClosest(list.get(mid),list.get(mid+1),target);

                // Repeat for right half
                low = mid+1;
            }

        }
        return list.get(mid);
    }

    public static int getClosest(int val1, int val2,
                                 int target)
    {
        if (target - val1 >= val2 - target)
            return val2;
        else
            return val1;
    }

    private int binarySearch2(int target, List<Integer> list, int low, int high) {

        // to find nearest index
        if (target < list.get(low)) {
            return list.get(low) - target;
        }
        int mid = low + (high - low) / 2;

        if (target == list.get(mid)) {
            return list.get(mid);
        }

        if (low >= high) {
            return low;
        }

        // target lesser than mid and lesser than mid-1 as well
        else if (list.get(mid) > target) {
            return binarySearch(target, list, low, mid - 1);
        }
        // target lies in btw mid and mid-1 , target =4 , a[mid]=7 , a[m-1]=3
        else if (mid - 1 > 0 && list.get(mid) > target && list.get(mid - 1) < target) {
            // dist btw a[mid] & target
            int x = Math.abs(list.get(mid) - target);
            // dist btw a[mid-1] & target
            int y = Math.abs(list.get(mid - 1) - target);

            if (x > y) {
                return list.get(mid - 1);
            } else {
                return list.get(mid);
            }
        }
        // target lies in btw mid and mid+1 , target =4 , a[mid]=7 , a[m-1]=3
        else if (list.get(mid) < target && list.get(mid + 1) > target) {
            // dist btw a[mid] & target
            int x = Math.abs(list.get(mid) - target);
            // dist btw a[mid+1] & target
            int y = Math.abs(list.get(mid + 1) - target);

            if (x > y) {
                return list.get(mid + 1);
            } else {
                return list.get(mid);
            }
        }
        return binarySearch(target, list, mid + 1, high);
    }

    public int[] shortestToChar3(String S, char C) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == C)
                list.add(i);
        }
        Integer[] arr = list.toArray(new Integer[list.size()]);
        int[] res = new int[S.length()];
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) != C) {
                res[i] = getMinDis(arr, i);
            }
        }
        return res;
    }

    public int getMinDis(Integer[] arr, int cur) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            if(arr[mid] > cur)
                right = mid;
            else
                left = mid;
        }
        return Math.abs(Math.min(arr[right] - cur, cur - arr[left]));
    }

    public static void main(String[] args) {
        ShortestDistanceToCharacter obj = new ShortestDistanceToCharacter();
        System.out.println(obj.shortestToChar("loveleetcode", 'e'));
    }

}