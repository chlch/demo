package com.clc.common;

import java.util.ArrayList;
import java.util.List;

/*
* jdk1.8及以后无效， MaxPermSize被移除
*  VM : -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
