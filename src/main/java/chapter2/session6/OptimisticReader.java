package chapter2.session6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class OptimisticReader implements Runnable {

    private final Position position;

    // ReadWriteLock
    //
    // 인터페이스 ReadWriteLock은 읽기와 쓰기 접근에 대한 대해 락을 운영할 수 있는 새로운 타입의 락이다.
    // 이 아이디어는 변하지 않는 변수를 여럿이 동시에 읽기만 할 때는 대개 문제가 발생하지 않고 쓸때만 문제가 발생한다는 점에 착안해 나왔다.
    // 그러므로 읽기-락은 동시에 여러 스레드에 의해 특정 스레드가 쓰기 락을 요청하지 않는 한 계속 획득될 수 있다.
    // 이런 방식으로 쓰기보다 읽기가 더 빈번히 일어나는 상황에서는 성능을 높일 수 있다.

    // StampedLock
    // 자바 8은 새로운 종류의 락을 하나 또 가져왔는데, 그 이름은 StampedLock이다.
    // 이 또한 마찬가지로 읽기, 쓰기 락을 지원한다.
    // 그러나 ReadWriteLock과는 대조적으로 락을 구하는
    // 메서드는 Long 타입의 stamp를 리턴한다(Timestamp의 stamp...와 유사).
    // 이 stamp 값을 이용해서 락을 해제하거나 락이 아직 타당한지를 확인해 볼 수 있다.
    // 게다가 stamped locks은 optimistic locking이라 불리는 또다른 락 모드를 지원한다.
    private final StampedLock lock;

    public OptimisticReader(Position position, StampedLock lock) {
        this.position = position;
        this.lock = lock;
    }

    @Override
    public void run() {
        long stamp;
        for (int i=0; i<100; i++) {
            try {
                stamp = lock.tryOptimisticRead();
                int x = position.getX();
                int y = position.getY();

                if (lock.validate(stamp)) {
                    System.out.println("OptimisticReader " + stamp + " ( " + x + " , " + y  + " )");
                } else {
                    System.out.println("OptimisticReader " + stamp + " - Not Free");
                }

                TimeUnit.MICROSECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
