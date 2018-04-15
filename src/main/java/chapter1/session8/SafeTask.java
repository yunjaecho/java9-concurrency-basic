package chapter1.session8;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Thread Local variable
 */
public class SafeTask implements Runnable {


    private static ThreadLocal<Date> startDate = new ThreadLocal<>() {
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };

   @Override
    public void run() {
        System.out.println("Starting Thread: " + Thread.currentThread().getId() + " : " + startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread Finishing: " + Thread.currentThread().getId() + " : " + startDate.get());
    }
}
