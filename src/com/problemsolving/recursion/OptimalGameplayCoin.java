/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: OptimalGameplayCoin.java, v 0.1 2020-10-13 22:32 paras.chawla Exp $$
 * https://www.youtube.com/watch?v=h7WI98qKkN0
 */

/* int array[] { 1,1,9,3,2,1,7,8 }
                 ^             ^
                 l             r

P1 can choose either array[l] or array[r]
Case 1) If P1 chooses array[l]

        1,9,3,2,1,7,8
        ^           ^
        l+1         r
  P2 will choose either l+1 or r
  P2 will choose maxOptimalSol out of l+1 or r so that min is required for next player
  Min(f(l+2,r),f(l+1,r-1))

Case 2) If P1 chooses array[r]

        1,1,9,3,2,1,7
        ^           ^
        l           r-1
  P2 will choose either l or r-1
  P2 will choose maxOptimalSol out of l or r-2 so that min is left for next player
  Min(f(l+2,r),f(l+1,r-1))

Approach 2 -
***Stack Overflow***
https://stackoverflow.com/questions/22195300/understanding-solution-to-finding-optimal-strategy-for-game-involving-picking-po
If I play left, I will immediately gain coin[left]. The other player now has to play between left+1 and right. He plays to maximize his 
gain. However since the number of coin is fixed, this amounts to minimize mine. Note that

if he plays left+1 I'll gain max_coin(coin, left+2, right)
if he plays right Ill gain max_coin(coin, left+1, right-1)
Since he tries to minimize my gain, I'll gain the minimum of those two.

Approach 3-
https://bitbucket.org/StableSort/play/src/master/src/com/stablesort/challenge/potsofgold/PotsOfGoldOrderN.java

*/
public class OptimalGameplayCoin {

    public static void main(String[] args) {
        OptimalGameplayCoin obj = new OptimalGameplayCoin();
        System.out.println(obj.optimalSum(new int[] {1, 1, 9, 3, 5}));
    }

    private int optimalSum(int[] coins) {
        //return maxProfit(coins, 0, coins.length - 1);
        return maxProfitMemo(coins, 0, coins.length - 1, new HashMap<String, Integer>());
    }

    // Recursive - Approach 1 -TOP DOWN
    private int maxProfit(int[] coins, int l, int r) {
        if (l > r || l >= coins.length || r <= 0) {
            return 0;
        }
        // Alice will make left choice
        // Bob will then choose l+1 leaving l+2,.,.,r or choose r leaving l+1,..,.,r-1 for Alice.. Bob will make sure to leave Min of 2
        // above 2 choices
        // maxProfit(coins, l + 2, r) && maxProfit(coins, l + 1, r - 1) is next chance of Alice
        int leftChoice = coins[l] + Math.min(maxProfit(coins, l + 2, r), maxProfit(coins, l + 1, r - 1));

        int rightChoice = coins[r] + Math.min(maxProfit(coins, l + 2, r - 1), maxProfit(coins, l, r - 2));

        return Math.max(leftChoice, rightChoice);
    }

    // Recursive + Memo- Approach 2 -TOP DOWN Memo
    private int maxProfitMemo(int[] coins, int l, int r, Map<String, Integer> map) {

        if (l > r || l >= coins.length || r <= 0) {
            return 0;
        }

        String key = l + "|" + r;
        Integer sol = map.get(key);
        if (sol != null) {
            return sol;
        }

        // Alice will make left choice
        // Bob will then choose l+1 leaving l+2,.,.,r or choose r leaving l+1,..,.,r-1 for Alice.. Bob will make sure to leave Min of 2
        // above 2 choices
        // maxProfit(coins, l + 2, r) && maxProfit(coins, l + 1, r - 1) is next chance of Alice
        int leftChoice = coins[l] + Math.min(maxProfitMemo(coins, l + 2, r, map), maxProfitMemo(coins, l + 1, r - 1, map));

        int rightChoice = coins[r] + Math.min(maxProfitMemo(coins, l + 2, r - 1, map), maxProfitMemo(coins, l, r - 2, map));

        int profit = Math.max(leftChoice, rightChoice);
        map.put(key, profit);
        return profit;
    }

}