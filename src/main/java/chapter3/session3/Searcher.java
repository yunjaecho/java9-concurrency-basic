package chapter3.session3;

import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {
    private final int firstRow;

    private final int lastRow;

    private final MatrixMock mock;

    private final Results results;

    private final int number;

    private final CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
        this.barrier = barrier;
    }


    @Override
    public void run() {
        int counter;

        System.out.printf("%s: processing lines from %d to %d\n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i=firstRow; i<lastRow; i++) {
            int row[] = mock.getRow(i);
            counter = 0;
            for (int j=0; j<row.length;j++) {
                if (row[j] == number) {
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        System.out.printf("%s: Lines processed\n", Thread.currentThread().getName());

        try {
            barrier.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
