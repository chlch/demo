package com.clc.common.basic.reference;

import java.io.File;
import java.util.Objects;

/**
 * jhat -J-Xmx1g -port 8000 strongRefBeforeGCEligible.hprof
 *
 * 启动一个web服务查看 具体引用细节
 */

public class StrongReference {
    private static final int RETRY = 3;
    public static void main(String[] args) throws InterruptedException {
        deleteOldDumps();
        A a = new A("Strong Reference");
        HeapDump.dumpHeap("common/heap-dumps/strongRefBeforeGCEligible.hprof", false);
        a = null; // Make the strong reference eligible for GC
        HeapDump.dumpHeap("common/heap-dumps/strongRefBeforeGC.hprof", false);
        runGC();
        HeapDump.dumpHeap("common/heap-dumps/strongRefAfterGC.hprof", false);
    }
    static class A {
        A(String s) {
            this.s = s;
        }
        String s;
    }
    static void deleteOldDumps() throws InterruptedException {
        boolean isDeleted = deleteFiles();
        int attempt = 0;
        while (!isDeleted && attempt++ < RETRY) {
            isDeleted = deleteFiles();
            Thread.sleep(1000 * attempt);
        }
    }
    private static boolean deleteFiles() {
        File dir = new File("common/heap-dumps");
        File[] files = dir.listFiles();
        if (files == null) return true;
        for (File file : Objects.requireNonNull(files)) {
            file.delete();
        }
        return files.length != 0;
    }
    static void runGC() throws InterruptedException {
        System.out.println("Running GC..");
        System.gc(); // Hint to run gc
        Thread.sleep(2000L); // sleep hoping to let GC thread run
        System.out.println("Finished running GC..");
    }
}
