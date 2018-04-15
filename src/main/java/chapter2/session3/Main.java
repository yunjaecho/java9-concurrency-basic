package chapter2.session3;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Running example with fair-mode=false");
        testPrintQueue(false);
        System.out.println("Running example with fair-mode=true");
        testPrintQueue(true);
    }

    private static void testPrintQueue(boolean fairMode) throws InterruptedException {
        PrintQueue printQueue = new PrintQueue(fairMode);

        Thread thread[] = new Thread[10];

        for(int i=0; i<10;i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        for(int i=0; i<10;i++) {
            thread[i].start();
            TimeUnit.MILLISECONDS.sleep(100);
        }

        for(int i=0; i<10;i++) {
            thread[i].join();
        }
    }
}
