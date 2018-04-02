package chapter2.session5;

public class Main {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 50);

        Buffer buffer = new Buffer(20);

        Producer producer = new Producer(mock, buffer);

        Thread producerThread = new Thread(producer, "producer");

        Consumer[] consumers = new Consumer[3];
        Thread[] consumersThread = new Thread[3];
        for (int i=0; i<3;i++) {
            consumers[i] = new Consumer(buffer);
            consumersThread[i] = new Thread(consumers[i], "Consumer" +i);
        }

        producerThread.start();
        for (int i=0;i<3;i++) {
            consumersThread[i].start();
        }

    }
}
