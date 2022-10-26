package com.ouyang;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.omg.CORBA.INTERNAL;
import org.springframework.data.redis.connection.RedisZSetCommands;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    private int key,val;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    char cur = 'A';

    public static void main(String[] args) throws InterruptedException {
        String waybil = "JDVA15171358091-2-3-";
        String str1 = waybil.substring(0, waybil.indexOf("-"));
        String subText = waybil.substring(waybil.indexOf("-"));
        String[] str = subText.split("-");
        Integer inte = Integer.parseInt(str[1]);
        System.out.println(inte);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int minLen = 0;
        int curLen = 1;
        int curNum = nums[0];
        while(right<nums.length){
            if(curNum>=target){
                if(left==right) return 1;
                minLen = minLen == 0 ? curLen : Math.min(minLen,curLen);
                curNum-=nums[left++];
            }else{
                if(right==nums.length-1){
                    return minLen;
                }else{
                    curNum+=nums[++right];
                }
            }
        }
        return minLen;

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        Map<Integer,Integer> tp = new HashMap<>();
        Stack<Integer> stack = new Stack<>();



        for(int i =0;i<prerequisites.length;i++){
            int cur= prerequisites[i][0];
            int need = prerequisites[i][1];
            tp.put(cur,tp.getOrDefault(cur,0)+1);
            List<Integer> list = map.getOrDefault(need,new ArrayList<>());
            list.add(cur);
            map.put(need,list);

        }

        for(int i : tp.keySet()){
            int du = tp.get(i);
            if(du == 0){
                stack.push(i);
            }
        }

        while(stack.size()>0){
            int cur = stack.pop();
            List<Integer> list = map.get(cur);
            if(list != null && list.size()>0){
                for(int i =0;i<list.size();i++){
                    int needjian = list.get(i);
                    tp.put(needjian,tp.get(needjian)-1);
                    if(tp.get(needjian) == 0){
                        stack.push(needjian);
                    }
                }

            }
        }

        for(int i : tp.keySet()){
            int du = tp.get(i);
            if(du != 0){
                return false;
            }
        }

        return true;

    }

    public static int hashCodeMine(String s){
        char[] chars = s.toCharArray();
        int hash = 0;
        for (int i = 0; i < chars.length; i++) {
            hash = hash*17 + chars[0];
        }
        return hash;
    }

    public void partition(int[] arr){
        int p1 = 0;
        int p2 = arr.length-1;
        int cur = 0;

        int cnt = 0;
        while(p2>=cur){
            if(arr[cur] == 2){
                swap(arr,p1++,cur++);
            }else if(arr[cur] == 1){
                swap(arr,p2--,cur);
            }else{
                cur++;
            }
            cnt++;
        }
        System.out.println("操作数:"+cnt);
    }

    public void swap (int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
