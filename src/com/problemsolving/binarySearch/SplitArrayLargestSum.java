/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

/**
 * @author paras.chawla
 * @version $Id: SplitArrayLargestSum.java, v 0.1 2021-03-14 2:17 PM paras.chawla Exp $$
 *
 * The min starts there because we're looking for the sum of the largest group in the final set of groups.
 * And no matter what groups you create, the largest sum of the subarray
 * must be greater than or equal to the largest value in the original input array.
 *
 * Calculate the midpoint between min and max. This midpoint is the group size we're going to try out to see how well it performs.
 *
 * Split the nums list into groups such that no group has a value larger than the chosen midpoint.
 * Note that we may end up with too many or too few groups. That's fine.
 *
 * Compare the number of groups we created against the target m. If we created too many groups, we know the final answer must be between
 * mid+1 and max. That's because we need fewer groups and the way to achieve fewer groups is to increase the allowed maximum sum in each
 * group.
 *
 * On the other hand, if the number of groups is too small, we know the final answer is between min and mid-1 because we need to increase
 * the number of groups which means the target sum is something smaller than the one we used. This is actually also a possible answer
 * assuming m is valid because you can always take any group and split it up to make more groups, so the mid value you targeted is at
 * worst, higher than the real value.
 *
 * On the third hand, if the number of groups is just right, we have a possible answer, so remember that answer. However, we should keep
 * searching just in case there is a better answer. We're ultimately looking for smaller maximum sums, so the potentially better answer
 * is between min and mid-1.
 *
 * Repeat the process until there is nothing else to search. Then use the minimum value we found during the above process.
 *
 */
public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int m) {

        // base condition
        if(m > nums.length){
            return -1;
        }

        // find total sum = end and start = max of array
        int totalSum=0, maxNum=-1;

        for(int num: nums){
            totalSum+=num;
            maxNum= Math.max(maxNum,num);
        }

        int start= maxNum, end = totalSum;

        // perform binary search
        int result=-1;
        while(start<= end){

            int mid = start + (end -start)/2;

            // check for mid,if it follows the equation
            if(isValid(mid,nums,m)){
                result= mid;
                end = mid-1;
            } else{
                start=mid+1;
            }
        }
        return result;
    }

    // result =21 , k=2
    // S1=> 7+2+5+10(Fail)
    // S2=> 10+8
    // return true
    private boolean isValid(int result, int[] nums, int k){

        int cnt=1;
        int sum=0;

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];

            if(sum > result){
                cnt++;
                sum=nums[i];
            }

            if(cnt>k){
                return false;
            }
        }

        return true;

    }
}