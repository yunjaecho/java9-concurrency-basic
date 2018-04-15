package chapter1.session6;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * User Thread
 */
public class WriteTask implements Runnable {
    private Deque<Event> deque;

    public WriteTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i=1; i<100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(
                    String.format("The thread %s has generated the event %d event", Thread.currentThread().getName(),  Thread.currentThread().getId(),1)
            );
            deque.addFirst(event);


            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
