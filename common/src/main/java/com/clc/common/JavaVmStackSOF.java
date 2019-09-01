package com.clc.common;
/*
* VM : -Xss128k
 */
public class JavaVmStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVmStackSOF o = new JavaVmStackSOF();
        try {
            o.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length : " + o.stackLength);
            throw e;
        }
    }
}
