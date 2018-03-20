package chapter1.session2;

import java.util.concurrent.TimeUnit;

public class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number = 1;

        while(true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }

            if (isInterrupted()) {
                System.out.println("The Prime Generator has been Interrupted");
                return;
            }

            number++;
        }

    }

    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.interrupt();

        System.out.println("*****************************");
        System.out.printf("task : Status %s\n", task.getState());
        System.out.printf("task : isInterrupted %s\n", task.isInterrupted());
        System.out.printf("task : isAlive %s\n", task.isAlive());
        System.out.printf("task : Status %s\n", task.getState());
        System.out.println("*****************************");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
