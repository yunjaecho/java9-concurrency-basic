package chapter1.session9;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 2 * Runtime.getRuntime().availableProcessors();

        MyThreadGroup threadGroup = new MyThreadGroup("My ThreadGroup");

        Task task = new Task();

        for (int i =0; i< numberOfThreads; i++) {
            Thread t = new Thread(threadGroup, task);
            t.start();
        }

        System.out.println("Number of threads :" + threadGroup.activeCount());
        System.out.println("Information about the Thread Group");
        threadGroup.list();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i=0; i<threadGroup.activeCount(); i++) {
            System.out.println("Thread : " + threads[i].getName() + " : " + threads[i].getState());
        }
    }

}
