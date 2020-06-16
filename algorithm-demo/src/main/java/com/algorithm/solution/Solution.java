package com.algorithm.solution;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
 *
 * @author ：Mr.Li
 * @date ：Created in 2020/4/8 12:33
 */
public class Solution {

    public static void main(String[] args) {
        int nums[] = {5,13, 7,11, 15,2, 6};
        int target=9;
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length-1; j > 0; j--) {
                if (nums[i]+nums[j]==target){
                    System.out.println("两数字的下标分别为"+i);
                    System.out.println("两数字的下标分别为"+j);

                }
            }
        }*/

        int[] ints = twoSum(nums, target);
        System.out.println(ints.toString());
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];

        // 建立k-v ，一一对应的哈希表
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target-nums[i],i);
        }

        return indexs;
    }
}
