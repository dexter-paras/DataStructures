import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


class Result {

    /*
     * Complete the 'longestVowelSubsequence' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int longestVowelSubsequence(String s) {

        if (s.length() <= 0) throw new IllegalArgumentException();
        Map<Character, Character> charMap = new HashMap<>();
        charMap.put('u', 'o');
        charMap.put('o', 'i');
        charMap.put('i', 'e');
        charMap.put('e', 'a');

        Map<Character, String> longestMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            //get longest subs
            String curLongestSubSeq;
            String preLongestSubSeq = null;
            if (c == 'a') {
                curLongestSubSeq = longestMap.getOrDefault(c, "");
            } else {
                curLongestSubSeq = longestMap.get(c);
                char precChar = charMap.get(c);
                preLongestSubSeq = longestMap.get(precChar);
            }

            //update running longest subsequence map
            if (preLongestSubSeq == null && curLongestSubSeq != null) {
                updateMap(curLongestSubSeq, c, longestMap);
            } else if (curLongestSubSeq == null && preLongestSubSeq != null) {
                updateMap(preLongestSubSeq, c, longestMap);
            } else if (curLongestSubSeq != null && preLongestSubSeq != null) {
                //pick longer
                if (curLongestSubSeq.length() < preLongestSubSeq.length()) {
                    updateMap(preLongestSubSeq, c, longestMap);
                } else {
                    updateMap(curLongestSubSeq, c, longestMap);
                }
            }
        }

        if (longestMap.get('u') == null) {
            return 0;
        }
        return longestMap.get('u').length();
    }

    private static void updateMap(String longestSub, char currChar,
                                  Map<Character, String> longestMap)    {
        String currSub = longestSub + currChar;
        longestMap.put(currChar, currSub);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int result = Result.longestVowelSubsequence(s);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
