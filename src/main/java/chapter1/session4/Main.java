package chapter1.session4;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Author : yunjae.cho
 * Thread Interrupt
 * Two case : first is Runnable implements ,
 *            second is Lambda expression
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("Runnable Interface implements");
        System.out.println("=================================");
        ConsoleClock clock = new ConsoleClock();
        Thread thread = new Thread(clock);

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        System.out.println("=================================");
        System.out.println("Lambda  expression");
        System.out.println("=================================");

        Thread thread2 = new Thread(() -> {
            IntStream.range(1, 10).forEach(i -> {
                System.out.println(new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Lambda : The File Clock has been interrupted");
                }
            });
        });

        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.interrupt();


    }

}
