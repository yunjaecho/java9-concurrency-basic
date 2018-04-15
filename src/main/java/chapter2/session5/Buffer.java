package chapter2.session5;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final LinkedList<String> buffer;

    private final int maxSize;

    // ReentrantLock 은 Lock 인터페이스를 구현한다.
    // synchronized 구문 과 동일한 메모리 가시성과 상호 배제 기능을 제공한다.
    // ReentrantLock 은 finally 구문에서 반드시 락을 해제해 주어야 한다.
    private final ReentrantLock lock;

    private final Condition lines;

    private final Condition space;

    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while(buffer.size() == maxSize) {
                space.wait();
            }
            buffer.offer(line);
            System.out.printf("%s : Insert Line : %d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String line = null;
        lock.lock();

        try {
            while ((buffer.size() == 0) && (hasPendingLines())) {
                lines.wait();
            }

            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s : Line Readed : %d\n", Thread.currentThread().getName(), buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return line;

    }

    public synchronized void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

}
