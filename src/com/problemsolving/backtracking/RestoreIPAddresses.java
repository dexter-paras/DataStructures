/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author paras.chawla
 * @version $Id: RestoreIPAddresses.java, v 0.1 2020-12-27 7:57 PM paras.chawla Exp $$
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }

        List<String> list = new ArrayList<>();
        helper(s, result, list);
        return result;
    }

    // "11212511" -> "1212511" -> "212511" ->"12511"
    private void helper(String s, List<String> result, List<String> builder) {

        if (builder.size() >= 4 && !s.isEmpty()) {
            return;
        } else if (builder.size() == 4 && s.isEmpty()) {
            //result.add(composeIp(builder));
            result.add(String.join(".",builder));
            return;
        }

        for (int i = 0; i < 3; i++) {
            String octet;
            if (s.length() >= i + 1) {
                octet = s.substring(0, i + 1);
            } else {
                break;
            }
            if (isValidOctet(octet)) {
                builder.add(octet);
                helper(s.substring(i + 1), result, builder);
                builder.remove(builder.size() - 1);
            }
        }

    }

    private String composeIp(List<String> builder) {
        String str = "";
        for (int i = 0; i < builder.size(); i++) {
            str += builder.get(i);
            if (i != 3) { str += "."; }
        }
        return str;
    }

    private boolean isValidOctet(String octet) {
        boolean bool = Integer.parseInt(octet) >= 0 && Integer.parseInt(octet) <= 255;

        if (octet.charAt(0) == '0' && octet.length() == 1) {
            return bool;
        } else if (octet.charAt(0) == '0' && octet.length() >= 2) {
            return false;
        }
        return bool;
    }

    // Approach -2
    public List<String> restoreIpAddressesSol2(String rawIpString) {
        List<String> restoredIps = new ArrayList<>();
        restoreIps(0, 0, new int[4], rawIpString, restoredIps);

        return restoredIps;
    }

    private void restoreIps(int progressIndex,
                            int currentSegment,
                            int[] ipAddressSegments,
                            String rawIpString,
                            List<String> restoredIps
    ) {
    /*
      If we have filled 4 segments (0, 1, 2, 3) and we are on the 4th,
      we will only record an answer if the string was decomposed fully
    */
        if (currentSegment == 4 && progressIndex == rawIpString.length()) {
            restoredIps.add(buildIpStringFromSegments(ipAddressSegments));
        } else if (currentSegment == 4) {
            return;
        }

    /*
      Generate segments to try, a segment can be 1 to 3 digits long.
    */
        for (int segLength = 1; segLength <= 3 && progressIndex + segLength <= rawIpString.length(); segLength++) {

            // Calculate 1 index past where the segment ends index-wise in the original raw ip string
            int onePastSegmentEnd = progressIndex + segLength;

            // Extract int value from our snapshot from the raw ip string
            String segmentAsString = rawIpString.substring(progressIndex, onePastSegmentEnd);
            int segmentValue = Integer.parseInt(segmentAsString);

            // Check the "snapshot's" validity - if invalid break iteration
            if (segmentValue > 255 || segLength >= 2 && segmentAsString.charAt(0) == '0') {
                break;
            }

            // Add the extracted segment to the working segments
            ipAddressSegments[currentSegment] = segmentValue;

            // Recurse on the segment choice - when finished & we come back here, the next loop iteration will try another segment
            restoreIps(progressIndex + segLength, currentSegment + 1, ipAddressSegments, rawIpString, restoredIps);
        }
    }

    private String buildIpStringFromSegments(int[] ipAddressSegments) {
        return ipAddressSegments[0] + "." + ipAddressSegments[1] + "." + ipAddressSegments[2] + "." + ipAddressSegments[3];
    }

    // https://leetcode.com/problems/restore-ip-addresses/discuss/966413/2ms-Simple-Java-Solution
    public List<String> restoreIpAddressesSol3(String s) {
        List<String> out = new ArrayList<>();

        for (int i = 0; i <= 3 && i < s.length(); i++) {

            String part1 = s.substring(0, i);
            if (!isValid(part1)) {
                continue;
            }
            for (int j = i + 1; j <= i + 3 && j < s.length(); j++) {
                String part2 = s.substring(i, j);
                if (!isValid(part2)) {
                    continue;
                }
                for (int k = j + 1; k <= j + 3 && k < s.length(); k++) {
                    String part3 = s.substring(j, k);
                    if (!isValid(part3)) {
                        continue;
                    }
                    String part4 = s.substring(k);
                    if (!isValid(part4)) {
                        continue;
                    }

                    StringJoiner sj = new StringJoiner(".");
                    sj.add(part1);
                    sj.add(part2);
                    sj.add(part3);
                    sj.add(part4);

                    out.add(sj.toString());
                }
            }

        }

        return out;
    }

    static boolean isValid(String part) {
        return part.length() > 0 && part.length() <= 3 && !(part.startsWith("0") && part.length() > 1)
                && Integer.valueOf(part) <= 255;
    }


    public static void main(String[] args) {
        RestoreIPAddresses obj = new RestoreIPAddresses();
        obj.restoreIpAddressesSol3("11212511");
    }
}