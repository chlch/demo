package com.clc.common;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    static class A {
        String a;
    }
    public static void main(String[] args) {
//        List<OOMObject> list = new ArrayList<>();
//        while(true) {
//            list.add(new OOMObject());
//        }
        System.out.println(sortArrayByParityII(new int[]{4,2,5,7}));
    }

    public static List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        char[] arr = s.toCharArray();
        int left = 0, right = 0;
        for (char c : arr) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left != 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        int vLeft =0, vRight = 0;
        for(int i=0; i<arr.length; i++) {
            if(vRight > vLeft) {
                break;
            }
            String t = s.substring(0,i);
         //   helper(i,arr,left,right,t,vLeft,vRight,res);
            if(arr[i] == '(') {
                vLeft++;
            } else if (arr[i] == ')') {
                vRight++;
            }
        }
        if(res.isEmpty()) {
            res.add("");
        }
        return res.stream().collect(Collectors.toList());
    }
    public static int[] sortArrayByParityII(int[] A) {
        int temp = -1, i=0;
        while(i != A.length) {

            if(A[i] % 2 != i%2) {
                temp = A[i];
            }
            if(temp != -1) {
                for(int k=i+1; k<A.length; k++) {
                    if(temp % 2 != A[k] % 2) {
                        A[i] = A[k];
                        A[k] = temp;
                        temp = -1;
                    }
                }
            }

            i++;
        }

        return A;
    }
}

