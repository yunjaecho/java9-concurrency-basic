package chapter3.session3;

import java.util.concurrent.CyclicBarrier;

/**
 *   CyclicBarrier 를 사용하면 동시에 실행되는 쓰레드 내부 원하는 지점에서 대기를 걸어주고 모든 쓰레드가 대기 상태에 들어갔을때,
 *   대기를 풀어주는 동작을 할 수 있다.
 *
 *   예를들어 설명하면 N 개의 쓰레드가 돌고 있다고 가정하고 CyclicBarrier 를 생성할 때 인자값으로 N 을 준다.
 *   각각의 쓰레드 내에서 CyclicBarrier 의 await() 를 호출하고 호출 횟수가 N번에 도달했을 때,
 *   N개의 모든 쓰레드의 대기 상태가 해제된다.
 *
 *
 *
 *   <img src="http://cfile4.uf.tistory.com/image/2548383C540527B416B64D"><br>
 *
 */
public class Main {
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH= 5;
        final int PARTICIPANTS =5;
        final int LINES_PARTICIPANT=2000;

        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        Results results = new Results(ROWS);

        Grouper grouper = new Grouper(results);

        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS,grouper);

        Searcher searcher[] = new Searcher[PARTICIPANTS];

        for (int i =0; i<PARTICIPANTS; i++) {
            searcher[i] = new Searcher(i * LINES_PARTICIPANT,
                    (i * LINES_PARTICIPANT) + LINES_PARTICIPANT,
                    mock,
                    results,
                    5,
                    barrier);
            Thread thread = new Thread(searcher[i]);
            thread.start();
        }
        System.out.println("Main : The main thread has finished");
    }
}
