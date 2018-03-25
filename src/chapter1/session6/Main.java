package chapter1.session6;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Daemon Thread
 *  - WriteTask : User Thread
 *  - CleanerTask : Daemon Thread
 */
public class Main {
    public static void main(String[] args) {
        Deque<Event> deque = new ConcurrentLinkedDeque<>();

        WriteTask writer = new WriteTask(deque);
        for (int i=0; i< Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }

        CleanerTask cleaner =new CleanerTask(deque);
        cleaner.start();
    }
}
