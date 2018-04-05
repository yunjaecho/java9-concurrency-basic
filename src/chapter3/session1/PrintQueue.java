package chapter3.session1;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    private final Semaphore semaphore;
    private final boolean freePrinters[];
    private final Lock lockPrinters;

    public PrintQueue() {
        this.semaphore = new Semaphore(3);
        this.freePrinters = new boolean[]{true, true, true};
        this.lockPrinters = new ReentrantLock();
    }

    public void printJob(Object document) {
        try {
            semaphore.acquire();

            int assignedPrinter = getPrinter();

            Long duration = (long)(Math.random()*10);
            System.out.printf("%s - %s : PrintQueue : Printing a job in Printer %d during %d seconds\n",
                    new Date(), Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);

            freePrinters[assignedPrinter]=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private int getPrinter() {
        int ret = -1;

        try {
            lockPrinters.lock();

            for (int i=0; i<freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i]=false;
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPrinters.unlock();
        }

        return ret;
    }
}
