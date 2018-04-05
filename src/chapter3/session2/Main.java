package chapter3.session2;

/**
 * A synchronization aid that allows one or more threads to wait until
 * a set of operations being performed in other threads completes.

 * A CountDownLatch is initialized with a given count.
 * The await methods block until the current count reaches zero due to invocations of the countDown() method,
 * after which all waiting threads are released and any subsequent invocations of await return immediately.
 * This is a one-shot phenomenon -- the count cannot be reset.
 * If you need a version that resets the count, consider using a CyclicBarrier.
 *
 * A CountDownLatch is a versatile synchronization tool and can be used for a number of purposes.
 * A CountDownLatch initialized with a count of one serves as a simple on/off latch,
 * or gate: all threads invoking await wait at the gate until it is opened by a thread invoking countDown().
 * A CountDownLatch initialized to N can be used to make one thread wait until N threads have completed some action,
 * or some action has been completed N times.
 *
 * A useful property of a CountDownLatch is that it doesn't require that threads calling countDown wait for the count to reach zero before proceeding,
 * it simply prevents any thread from proceeding past an await until all threads could pass.
 *
 * Sample usage: Here is a pair of classes in which a group of worker threads use two countdown latches:
 *
 * The first is a start signal that prevents any worker from proceeding until the driver is ready for them to proceed;
 * The second is a completion signal that allows the driver to wait until all workers have completed.
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Videoconference class CountDownLatch count 지정
         * 해당 숫자만금 Participant Thread 에서 Videoconference.arrive 호출해서 CountDownLatch countDown 발생
         * Videoconference Thread wait 에서 깨어남
         */
        Videoconference conference = new Videoconference(10);

        Thread threadConference = new Thread(conference);
        threadConference.start();

        for(int i=0; i<10;i++) {
            Participant p = new Participant(conference, "Participant_" + i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
